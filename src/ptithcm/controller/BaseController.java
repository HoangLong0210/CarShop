package ptithcm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.bean.Mailer;
import ptithcm.dao.AdminDAO;
import ptithcm.dao.BrandDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.SecurityDAO;
import ptithcm.dao.TypeDAO;
import ptithcm.entity.Admin;
import ptithcm.entity.Brands;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;
import ptithcm.entity.Types;
import ptithcm.service.FilterService;


@Controller
public class BaseController {
	@Autowired
	FilterService filterService;
	@Autowired
	Mailer mailer;
	
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	BrandDAO brandDAO;
	@Autowired
	TypeDAO typeDAO;
	@Autowired
	SecurityDAO securityDAO;
	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping("index")
	public String index(ModelMap model, HttpServletRequest request) {
		filterService.clearFilterCar();
		filterService.clearFilterOrder();
		Admin user = (Admin) request.getSession().getAttribute("user");
		String logged;
		if(user==null) {
			logged = "0";
		}else if(user.getUsername().equals("sa")) {
			logged = "2";
		}else {
			logged = "1";
		}
		model.addAttribute("logged", logged);
		return "public/index";
	}
	
	@RequestMapping("login")
	public String login_form(HttpSession session, @ModelAttribute("user") Admin user) {
		if(session.getAttribute("user") != null) session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request, @ModelAttribute("user")Admin user) {
		user = adminDAO.getAdmin(user.getUsername(), user.getPass());
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(600);
			if(user.getUsername().equals("sa")) {
				return "redirect:/admin/";
			}else {
				return "redirect:/";
			}
		}
		model.addAttribute("message", "User invalid");
		return "login";
	}
	
	@RequestMapping("register")
	public String register_form(HttpSession session, @ModelAttribute("user") Admin user) {
		if(session.getAttribute("user") != null) session.removeAttribute("user");
		return "register";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(ModelMap model, HttpServletRequest request,@Validated @ModelAttribute("user")Admin user, BindingResult err) {
		if(err.getErrorCount()>0) {
			System.out.println(err.toString());
			return "register";
		}
		if(adminDAO.getAdmin(user.getEmail()) != null) {
			model.addAttribute("message", "Mail đã được sử dụng!");
		}else {
			Integer temp = adminDAO.create(user);
			if (temp != 0) {
				model.addAttribute("message", "Đăng kí thành công");
			} else {
				model.addAttribute("message", "Username đã được sử dụng.");
			}
		}
		return "register";
	}
	
	@RequestMapping("verified")
	public String verified(ModelMap model, @PathParam(value = "token") String token) {
		Securities security = securityDAO.getSecurity(token);
		if(security == null) {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận không tồn tại!");
			return "public/notify";
		}
		Long expiredDate = Long.parseLong(security.getExpired());
		Long currentDate = Calendar.getInstance().getTimeInMillis();
		
		if(expiredDate > currentDate) {
			if(security.getType().equals("O")) {
				Orders order = orderDAO.getOrder(security.getId());
				order.setStat(-1);
				Integer temp = orderDAO.update(order);
				if (temp != 0) {
					model.addAttribute("status", "1");
					model.addAttribute("order", order);
					model.addAttribute("message", "Đơn hàng của bạn đã được xác nhận.");
				} else {
					model.addAttribute("status", "0");
					model.addAttribute("message", "Không thể cập nhật trạng thái đơn hàng!");
				}
			}
			else if(security.getType().equals("U")) {
				byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
				token = new String(decodedBytes);
				Admin user = adminDAO.getAdmin(token);
				model.addAttribute("user", user);
				securityDAO.delete(security);
				return "changepass";
			}
			
			/*
			byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes()); 
			String[] decodeString = new String(decodedBytes).split("~~"); 
			String oid = decodeString[0];
			*/	
		}
		else {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận đã bị hết hạn!");
		}
		securityDAO.delete(security);
		return "public/notify";
	}
	
	@RequestMapping("changepass")
	public String changepass(ModelMap model, HttpServletRequest request, @ModelAttribute("user") Admin user) {
		String password=request.getParameter("passwordnew");
		user = adminDAO.getAdmin(user.getUsername(), user.getPass());
		user.setPass(password);
		Integer temp = adminDAO.update(user);
		if (temp != 0) {
			model.addAttribute("alerts", "Mật khẩu đã được thay đổi");
		} else {
			model.addAttribute("alerts", "Không thể cập nhật mật khẩu");
		}
		return "login";
	}
	
	@RequestMapping("getpass")
	public String getpass(ModelMap model, HttpServletRequest request) {
		String email=request.getParameter("email");
		String token = email;
		byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
		token = new String(encodedBytes);
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
		Securities security = new Securities();
		security.setToken(token);
		security.setExpired(String.valueOf(c.getTimeInMillis()));
		security.setCreated(new Date());
		security.setId(-1);
		security.setType("U");
		if(securityDAO.create(security)==0) {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Không thể tạo key!");
			return "public/notify";
		}
		
		String from = "IDRISCAR";
		String to = email;
		String subject = "Get Password";
		String body = "";
		try {
			body = "http://localhost:8080/CarShop/verified.htm?token=" + URLEncoder.encode(token, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			mailer.send(from, to, subject, body);
			model.addAttribute("status", "1");
			model.addAttribute("message", "Vui lòng vào mail để xác nhận việc lấy lại mật khẩu!");
		}catch (Exception e) {
			model.addAttribute("status", "0");
			model.addAttribute("message","Gửi mail thất bại!");
		}
		return "public/notify";
	}
	
	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		return brandDAO.getBrands();
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		return typeDAO.getTypes();
	}
}


