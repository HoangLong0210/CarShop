package ptithcm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.FilterCar;
import ptithcm.bean.FilterOrder;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.dao.CarDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.entity.Cars;
import ptithcm.entity.Orders;
import ptithcm.service.FilterService;
import ptithcm.service.PageService;


@Controller
@RequestMapping("/admin/")
public class AdminController{
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	PageNumber pagenumber;
	@Autowired
	FilterCar filterCar;
	@Autowired
	FilterOrder filterOrder;
	
	
	@Autowired
	PageService pageService;
	@Autowired
	FilterService filterService;

	@Autowired
	OrderDAO orderDAO;
	@Autowired
	CarDAO carDAO;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model, 
			@ModelAttribute("order") Orders order) {
		filterService.clearFilterCar();
		if(request.getParameter("clear") != null) {
			filterService.clearFilterOrder();
		}
		filterOrder = getFilterOrder(request);
		
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		List<Orders> orders = orderDAO.getOrders(filterOrder);
		model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@RequestMapping(value="/{id}.htm", params="linkAccept")
	public String accept(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderDAO.getOrder(id));
		model.addAttribute("orderAccept", "true");
		List<Orders> orders = orderDAO.getOrders(filterOrder);
		model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnAccept")
	public String accepted(HttpServletRequest request, ModelMap model, @ModelAttribute("order") Orders order) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		@SuppressWarnings("deprecation")
		String expecteddate = formatter.format(new Date(request.getParameter("expecteddate")));	
		
		Cars car = carDAO.getCar(order.getCar().getId());
		if(car.getAmount() < order.getAmount()) {
			model.addAttribute("message", "Purchase quantity exceeds available quantity!");
			List<Orders> orders = orderDAO.getOrders(filterOrder);
			model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
			return "admin/index";
		}
		order.setStat(1);
		Integer temp = orderDAO.update(order);
		if (temp != 0) {
			car.setAmount(car.getAmount()-order.getAmount());
			temp = carDAO.update(car);
			if(temp != 0) {
				model.addAttribute("message", "Order accepted");
				String from = "IDRISCAR";
				String to = order.getEmail();
				String subject = "Order Car";
				String body = "Đơn hàng " + order.getOid() + " đã được chấp nhận và dự kiến sẽ giao vào ngày " + expecteddate;
				if(temp != 0) {
					try {
						mailer.send(from, to, subject, body);
					}catch (Exception e) {
						model.addAttribute("message","Gửi mail thất bại!");
					}
				}
			}else {
				model.addAttribute("message", "Update car amount fail!");
			}
		} else {
			model.addAttribute("message", "Failed!");
		}
		

		List<Orders> orders = orderDAO.getOrders(filterOrder);
		model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkDeny")
	public String deny(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderDAO.getOrder(id));
		model.addAttribute("orderDeny", "true");
		List<Orders> orders = orderDAO.getOrders(filterOrder);
		model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnDeny")
	public String denied(HttpServletRequest request, ModelMap model, @ModelAttribute("order") Orders order) {
		String reason = request.getParameter("messagebody");
		order.setStat(2);
		Integer temp = orderDAO.update(order);
		if (temp != 0) {
			model.addAttribute("message", "Order rejected");
		} else {
			model.addAttribute("message", "Failed!");
		}		
		String from = "IDRISCAR";
		String to = order.getEmail();
		String subject = "Order Car";
		String body = "Đơn hàng " + order.getOid() + " đã bị từ chối.<br/>" + ((reason.length()>0)?"Lí do: " + reason + "<br/>":"")
				+ "Mọi thắc mắc vui lòng liên hệ qua website: http://localhost:8080/CarShop/";
		if(temp != 0) {
			try {
				mailer.send(from, to, subject, body);
			}catch (Exception e) {
				model.addAttribute("message","Gửi mail thất bại!");
			}
		}
		List<Orders> orders = orderDAO.getOrders(filterOrder);
		model.addAttribute(pageService.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@ModelAttribute("filter_order")
	public FilterOrder getFilterOrder(HttpServletRequest request) {
		filterOrder.setOidFilter((request.getParameter("oidFilter")==null)?filterOrder.getOidFilter():request.getParameter("oidFilter").trim());
		filterOrder.setCustomerFilter((request.getParameter("customerFilter")==null)?filterOrder.getCustomerFilter():request.getParameter("customerFilter").trim());
		filterOrder.setEmailFilter((request.getParameter("emailFilter")==null)?filterOrder.getEmailFilter():request.getParameter("emailFilter").trim());
		filterOrder.setPhoneFilter((request.getParameter("phoneFilter")==null)?filterOrder.getPhoneFilter():request.getParameter("phoneFilter").trim());
		filterOrder.setStatusFilter((request.getParameter("statusFilter")==null)?filterOrder.getStatusFilter():Integer.parseInt(request.getParameter("statusFilter").trim()));
		return filterOrder;
	}
	
	@ModelAttribute("status")
	public Map<Integer, String> status(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		 map.put(0, "All");
        map.put(-1, "Pending");
        map.put(2, "Denied");
        map.put(1, "Accepted");
		return map;
	}

}
