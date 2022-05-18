package ptithcm.bean;

public class FilterCar {
	private String nameFilter;
	private long minFilter, maxFilter;
	private String typeFilter;
	private String brandFilter;
	
	public FilterCar() {
		super();
	}
	
	public String getNameFilter() {
		return nameFilter;
	}
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
	}
	public long getMinFilter() {
		return minFilter;
	}
	public void setMinFilter(long minFilter) {
		this.minFilter = minFilter;
	}
	public long getMaxFilter() {
		return maxFilter;
	}
	public void setMaxFilter(long maxFilter) {
		this.maxFilter = maxFilter;
	}
	public String getTypeFilter() {
		return typeFilter;
	}
	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
	}
	public String getBrandFilter() {
		return brandFilter;
	}
	public void setBrandFilter(String brandFilter) {
		this.brandFilter = brandFilter;
	}
	
	
}
