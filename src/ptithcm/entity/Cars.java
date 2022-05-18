package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="Cars")
public class Cars {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique = true)
	@NotBlank(message="Tên xe không được bỏ trống")
	private String name;
	@NotBlank(message="Hình ảnh không được bỏ trống")
	private String img;
	private String video;
	@Min(value=0, message="Số lượng không hợp lệ")
	private int amount;
	@NotBlank(message="Mô tả không được bỏ trống")
	private String disc;
	@Min(value=0, message="Giá xe không hợp lệ")
	@Max(value=Long.MAX_VALUE, message="Giá xe không hợp lệ")
	private long price;
	
	@ManyToOne
	@JoinColumn(name="classId")
	private Types type;
	
	@ManyToOne
	@JoinColumn(name="brandId")
	private Brands brand;
	
	@OneToMany(mappedBy="car", fetch=FetchType.EAGER)
	private Collection<Orders> orders;
	
	
	
	public Collection<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	public Brands getBrand() {
		return brand;
	}
	public void setBrand(Brands brand) {
		this.brand = brand;
	}
	
	
}
