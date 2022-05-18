package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javafx.util.Pair;
import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.bean.UploadFile;
import ptithcm.dao.BrandDAO;
import ptithcm.dao.CarDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.TypeDAO;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Types;
import ptithcm.service.FileService;
import ptithcm.service.FilterService;
import ptithcm.service.PageService;

@Controller
@RequestMapping("/admin/cars/")
public class AdminCarController{
	@Autowired
	UploadFile uploadFile;
	@Autowired
	Mailer mailer;
	@Autowired
	PageNumber pagenumber;
	@Autowired
	FilterCar filterCar;
	
	@Autowired
	PageService pageService;
	@Autowired
	FileService fileService;
	@Autowired
	FilterService filterService;
	
	@Autowired
	CarDAO carDAO;
	@Autowired
	BrandDAO brandDAO;
	@Autowired
	TypeDAO typeDAO;
	@Autowired
	OrderDAO orderDAO;
	
	
	
	@RequestMapping("index")
	public String cars(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car) {
		filterService.clearFilterOrder();
		if(request.getParameter("clear") != null) {
			filterService.clearFilterCar();
		}
		filterCar = getFilterCar(request);
		List<Cars> cars = carDAO.getCars(filterCar);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, page, 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnCancel")
	public String cancel(HttpServletRequest request,ModelMap model, @ModelAttribute("car") Cars car) {
		List<Cars> cars = null;
		clearFormCar(car);
		cars = carDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnAdd")
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("car") Cars car, BindingResult err) {
		List<Cars> cars = null;
		if(imageFile != null) {
			car.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& car.getImg().equals("imageFile"))) {
				cars = carDAO.getCars(filterCar);
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
				return "admin/cars";
			}else {
				err = null;
			}
		}
		
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, car.getName() + "." + imageFile.getContentType().split("/")[1], "image", "car");
			if(!result.getKey()) {
				model.addAttribute("message", result.getValue());
				
				cars = carDAO.getCars(filterCar);
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
				return "admin/cars";
			}else {
				car.setImg(result.getValue());
			}
		}
		
		Integer temp = carDAO.create(car);
		if (temp != 0) {
			model.addAttribute("message", "Insert car successful");
		} else {
			model.addAttribute("message", "Insert car failed! This car maybe already in shop");
		}
		clearFormCar(car);
		cars = carDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("car") Cars car) {
		List<Cars> cars = carDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("car", carDAO.getCar(id));
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("car") Cars car, BindingResult err) {
		List<Cars> cars = null;
		if(imageFile != null) {
			car.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& car.getImg().equals("imageFile"))) {
				cars = carDAO.getCars(filterCar);
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
				return "admin/cars";
			}else {
				err = null;
			}
		}
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, car.getName() + "." + imageFile.getContentType().split("/")[1], "image", "car");
			if(!result.getKey()) {
				
				model.addAttribute("message", result.getValue());
				cars = carDAO.getCars(filterCar);
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
				return "admin/cars";
			}else {
				car.setImg(result.getValue());
			}
		}
		Integer temp = carDAO.update(car);
		if (temp != 0) {
			model.addAttribute("message", "Update successfull");
		} else {
			model.addAttribute("message", "Update failed!");
		}
		clearFormCar(car);
		cars = carDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "linkDelete")
	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car,
			@PathVariable("id") Integer id) {
		Integer temp = 0;
		if(orderDAO.getOrders(id).isEmpty()) {
			temp = carDAO.delete(car);
			if (temp == 1) {
				model.addAttribute("message", "Delete successfull");
			} else if (temp == 0){
				model.addAttribute("message", "Delete failed!");
			}
		}else {
			car = carDAO.getCar(id);
			car.setAmount(0);
			temp = carDAO.update(car);
			if (temp == 1) {
				model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
			} else {
				model.addAttribute("message", "Update amount failed!");
			}
		}
		List<Cars> cars = carDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}

	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		List<Brands> list = brandDAO.getBrands();
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		List<Types> list = typeDAO.getTypes();
		return list;
	}
	
	@ModelAttribute("brandsSearch")
	public List<Brands> getBrandsSearch() {
		List<Brands> list = brandDAO.getBrands();
		list.add(0, new Brands("All", "none"));
		return list;
	}
	
	@ModelAttribute("typesSearch")
	public List<Types> getTypesSearch() {
		List<Types> list = typeDAO.getTypes();
		list.add(0, new Types("All", "none"));
		return list;
	}
	
	@ModelAttribute("filter_car")
	public FilterCar getFilterCar(HttpServletRequest request) {
		filterCar.setNameFilter((request.getParameter("nameFilter")==null)?filterCar.getNameFilter():request.getParameter("nameFilter").trim());
		filterCar.setMinFilter((request.getParameter("minFilter")==null || request.getParameter("minFilter").length()==0)?filterCar.getMinFilter():Long.parseLong(request.getParameter("minFilter").trim()));
		filterCar.setMaxFilter((request.getParameter("maxFilter")==null || request.getParameter("maxFilter").length()==0)?filterCar.getMaxFilter():Long.parseLong(request.getParameter("maxFilter").trim()));
		filterCar.setTypeFilter((request.getParameter("typeFilter")==null)?filterCar.getTypeFilter():request.getParameter("typeFilter").trim());
		filterCar.setBrandFilter((request.getParameter("brandFilter")==null)?filterCar.getBrandFilter():request.getParameter("brandFilter").trim());
		return filterCar;
	}
	
	public void clearFormCar(Cars car) {
		car.setName("");
		car.setImg("");
		car.setVideo("");
		car.setAmount(0);
		car.setPrice(0);
		car.setDisc("");
	}
	
}
