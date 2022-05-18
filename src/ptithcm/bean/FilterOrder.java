package ptithcm.bean;

public class FilterOrder {
	private String oidFilter;
	private String emailFilter;
	private String phoneFilter;
	private String customerFilter;
	private int statusFilter;

	public int getStatusFilter() {
		return statusFilter;
	}
	public void setStatusFilter(int statusFilter) {
		this.statusFilter = statusFilter;
	}
	public String getOidFilter() {
		return oidFilter;
	}
	public void setOidFilter(String oidFilter) {
		this.oidFilter = oidFilter;
	}
	public String getEmailFilter() {
		return emailFilter;
	}
	public void setEmailFilter(String emailFilter) {
		this.emailFilter = emailFilter;
	}
	public String getPhoneFilter() {
		return phoneFilter;
	}
	public void setPhoneFilter(String phoneFilter) {
		this.phoneFilter = phoneFilter;
	}
	public String getCustomerFilter() {
		return customerFilter;
	}
	public void setCustomerFilter(String customerFilter) {
		this.customerFilter = customerFilter;
	}
	
	
}
