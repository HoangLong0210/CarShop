package ptithcm.bean;

import javax.persistence.Entity;

@Entity
public class Revenue {
	private String name;
	private Integer amount;
	private Long total;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(final Number amount) {
		this.amount = amount != null ? amount.intValue() : null;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Revenue(String name, Integer amount, Long total) {
		this.name = name;
		this.amount = amount;
		this.total = total;
	}
	public Revenue() {
		
	}
	
	
}
