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
import ptithcm.service.FileService;
import ptithcm.service.FilterService;
import ptithcm.service.PageService;

@Controller
@RequestMapping("/admin/brands/")
public class AdminBrandController{
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
	public String brands(HttpServletRequest request, ModelMap model, @ModelAttribute("brand") Brands brand) {
		filterService.clearFilterOrder();
		if(request.getParameter("clear") != null) {
			filterService.clearFilterCar();
		}
		filterCar = getFilterCar(request);
		List<Brands> brands = brandDAO.getBrands(filterCar.getNameFilter());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, page, 6));
		return "admin/brands";
	}
	
	@RequestMapping(value = "index.htm", params = "btnCancel")
	public String cancel(HttpServletRequest request,ModelMap model, @ModelAttribute("brand") Brands brand) {
		List<Brands> brands = null;
		clearFormBrand(brand);
		brands = brandDAO.getBrands(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
		return "admin/brands";
	}
	
	@RequestMapping(value = "index.htm", params = "btnAdd")
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("brand") Brands brand, BindingResult err) {
		List<Brands> brands = null;
		if(imageFile != null) {
			brand.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& brand.getImg().equals("imageFile"))) {
				brands = brandDAO.getBrands(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
				return "admin/brands";
			}else {
				err = null;
			}
		}
		
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, brand.getName() + "." + imageFile.getContentType().split("/")[1], "image", "brand");
			if(!result.getKey()) {
				model.addAttribute("message", result.getValue());
				
				brands = brandDAO.getBrands(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnAdd");
				model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
				return "admin/brands";
			}else {
				brand.setImg(result.getValue());
			}
		}
		
		Integer temp = brandDAO.create(brand);
		if (temp != 0) {
			model.addAttribute("message", "Insert brand successful");
		} else {
			model.addAttribute("message", "Insert brand failed! This brand maybe already in shop");
		}
		clearFormBrand(brand);
		brands = brandDAO.getBrands(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
		return "admin/brands";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("brand") Brands brand) {
		List<Brands> brands = brandDAO.getBrands(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("brand", brandDAO.getBrand(id));
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
		return "admin/brands";
	}
	
	@RequestMapping(value = "index.htm", params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile, @Validated @ModelAttribute("brand") Brands brand, BindingResult err) {
		List<Brands> brands = null;
		if(imageFile != null) {
			brand.setImg("imageFile");
		}
		if(err.hasErrors()) {
			if(!(err.getErrorCount() == 1 
					&& brand.getImg().equals("imageFile"))) {
				brands = brandDAO.getBrands(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
				return "admin/brands";
			}else {
				err = null;
			}
		}
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, brand.getName() + "." + imageFile.getContentType().split("/")[1], "image", "brand");
			if(!result.getKey()) {
				
				model.addAttribute("message", result.getValue());
				brands = brandDAO.getBrands(filterCar.getNameFilter());
				model.addAttribute("btnStatus", "btnEdit");
				model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
				return "admin/brands";
			}else {
				brand.setImg(result.getValue());
			}
		}
		Integer temp = brandDAO.update(brand);
		if (temp != 0) {
			model.addAttribute("message", "Update successfull");
		} else {
			model.addAttribute("message", "Update failed!");
		}
		clearFormBrand(brand);
		brands = brandDAO.getBrands(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
		return "admin/brands";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "linkDelete")
	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("brand") Brands brand,
			@PathVariable("id") Integer id) {
		Integer temp = 0;
		brand = brandDAO.getBrand(id);
		if(carDAO.getCarsByBrand(brand.getName()).isEmpty()) {
			temp = brandDAO.delete(brand);
			if (temp != 0) {
				model.addAttribute("message", "Delete successfull");
			} else if (temp == 0){
				model.addAttribute("message", "Delete failed!");
			}
		}else {
			model.addAttribute("message", "Cannot delete completely. This brand is used for car infomation.");
		}
		List<Brands> brands = brandDAO.getBrands(filterCar.getNameFilter());
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(brands, pagenumber.getP(), 6));
		return "admin/brands";
	}

	
	@ModelAttribute("filter_brand")
	public FilterCar getFilterCar(HttpServletRequest request) {
		filterCar.setNameFilter((request.getParameter("nameFilter")==null)?filterCar.getNameFilter():request.getParameter("nameFilter").trim());
		return filterCar;
	}
	
	public void clearFormBrand(Brands brand) {
		brand.setName("");
		brand.setImg("");
		brand.setDisc("");
	}
	
}
