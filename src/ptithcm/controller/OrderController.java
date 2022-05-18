package ptithcm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.dao.OrderDAO;
import ptithcm.entity.Admin;

@Controller
@RequestMapping("/orders/")
public class OrderController {
	@Autowired
	OrderDAO orderDAO;
	
	
	@RequestMapping("index")
	public String index(ModelMap model, HttpServletRequest request) {
		Admin user = (Admin) request.getSession().getAttribute("user");
		String logged;
		if(user==null) {
			logged = "0";
		}else if(user.getUsername().equals("sa")) {
			logged = "2";
		}else {
			logged = "1";
		}
		String oid = request.getParameter("oid");
		model.addAttribute("orders", orderDAO.getOrdersOfUser(user.getId(), (oid==null)?"":oid));
		model.addAttribute("logged", logged);
		return "public/orders";
	}
	
}
