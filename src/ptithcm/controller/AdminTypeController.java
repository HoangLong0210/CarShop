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
import ptithcm.entity.Types;
import ptithcm.service.FileService;
import ptithcm.service.FilterService;
import ptithcm.service.PageService;

@Controller
@RequestMapping("/admin/types/")
public class AdminTypeController{
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
	public String types(HttpServletRequest request, ModelMap model, @ModelAttribute("type") Types type) {
		filterService.clearFilterOrder();
		if(request.getParameter("clear") != null) {
			filterService.clearFilterCar();
		}
		filterCar = getFilterCar(request);
		List<Types> types = typeDAO.getTypes(filterCar.getNameFilter());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(types, page, 6));
		return "admin/types";
	}
	
	@RequestMapping(value = "index.htm", params = "btnCancel")
	public String cancel(HttpServletRequest request,ModelMap model, @ModelAttribute("type") Types type) {
		List<Types> types = null;
		clearFormType(type);
		types = typeDAO.getTypes(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
		return "admin/types";
	}
	
	@RequestMapping(value = "index.htm", params = "btnAdd")
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("type") Types type, BindingResult err) {
		List<Types> types = null;
		if(imageFile != null) {
			type.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& type.getImg().equals("imageFile"))) {
				types = typeDAO.getTypes(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
				return "admin/types";
			}else {
				err = null;
			}
		}
		
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, type.getName() + "." + imageFile.getContentType().split("/")[1], "image", "type");
			if(!result.getKey()) {
				model.addAttribute("message", result.getValue());
				
				types = typeDAO.getTypes(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
				return "admin/types";
			}else {
				type.setImg(result.getValue());
			}
		}
		
		Integer temp = typeDAO.create(type);
		if (temp != 0) {
			model.addAttribute("message", "Insert type successful");
		} else {
			model.addAttribute("message", "Insert type failed! This type maybe already in shop");
		}
		clearFormType(type);
		types = typeDAO.getTypes(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
		return "admin/types";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("type") Types type) {
		List<Types> types = typeDAO.getTypes(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("type", typeDAO.getType(id));
		model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
		return "admin/types";
	}
	
	@RequestMapping(value = "index.htm", params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("type") Types type, BindingResult err) {
		List<Types> types = null;
		if(imageFile != null) {
			type.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& type.getImg().equals("imageFile"))) {
				types = typeDAO.getTypes(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
				return "admin/types";
			}else {
				err = null;
			}
		}
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, type.getName() + "." + imageFile.getContentType().split("/")[1], "image", "type");
			if(!result.getKey()) {
				
				model.addAttribute("message", result.getValue());
				types = typeDAO.getTypes(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
				return "admin/types";
			}else {
				type.setImg(result.getValue());
			}
		}
		Integer temp = typeDAO.update(type);
		if (temp != 0) {
			model.addAttribute("message", "Update successfull");
		} else {
			model.addAttribute("message", "Update failed!");
		}
		clearFormType(type);
		types = typeDAO.getTypes(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
		return "admin/types";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "linkDelete")
	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("type") Types type,
			@PathVariable("id") Integer id) {
		Integer temp = 0;
		type = typeDAO.getType(id);
		if(carDAO.getCarsByType(type.getName()).isEmpty()) {
			temp = typeDAO.delete(type);
			if (temp != 0) {
				model.addAttribute("message", "Delete successfull");
			} else if (temp == 0){
				model.addAttribute("message", "Delete failed!");
			}
		}else {
			model.addAttribute("message", "Cannot delete completely. This type is used for car infomation.");
		}
		List<Types> types = typeDAO.getTypes(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(types, pagenumber.getP(), 6));
		return "admin/types";
	}

	
	@ModelAttribute("filter_type")
	public FilterCar getFilterCar(HttpServletRequest request) {
		filterCar.setNameFilter((request.getParameter("nameFilter")==null)?filterCar.getNameFilter():request.getParameter("nameFilter").trim());
		return filterCar;
	}
	
	public void clearFormType(Types type) {
		type.setName("");
		type.setImg("");
		type.setDisc("");
	}
	
}
