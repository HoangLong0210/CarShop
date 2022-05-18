package ptithcm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ptithcm.bean.FilterCar;
import ptithcm.bean.FilterOrder;
import ptithcm.service.FilterService;

public class FilterServiceImpl implements FilterService{

	@Autowired
	FilterCar filterCar;
	@Autowired
	FilterOrder filterOrder;
	
	@Override
	public void clearFilterCar() {
		filterCar.setNameFilter("");
		filterCar.setMinFilter(0);
		filterCar.setMaxFilter(Long.parseLong("1000000000000000"));
		filterCar.setTypeFilter("");
		filterCar.setBrandFilter("");
		
	}

	@Override
	public void clearFilterOrder() {
		filterOrder.setOidFilter("");
		filterOrder.setCustomerFilter("");
		filterOrder.setEmailFilter("");
		filterOrder.setPhoneFilter("");
		filterOrder.setStatusFilter(0);
		
	}

}
