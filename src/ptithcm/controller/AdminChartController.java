package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.Revenue;
import ptithcm.dao.CarDAO;
import ptithcm.dao.OrderDAO;

@Controller
@RequestMapping("/admin/chart/")
public class AdminChartController {
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	CarDAO carDAO;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model) {
		Integer year = Integer.parseInt((request.getParameter("year")==null)?"0":request.getParameter("year"));
		if(year == 0 && getListYear().size() > 0) {
			year = getListYear().get(0);
		}
		model.addAttribute("year", year);
		List<Revenue> revenues = orderDAO.getRevenues(year);
		model.addAttribute("revenues", revenues);
		return "admin/chart";
	}
	
	@ModelAttribute("list_year")
	public List<Integer> getListYear(){
		return orderDAO.getListYear();
	}
	
	@ModelAttribute("list_car")
	public List<String> getListCar(){
		return carDAO.getListCarName();
	}
}
