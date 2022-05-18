package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="Classs")
public class Types{
	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique = true)
	@NotBlank(message="Tên loại không được bỏ trống")
	private String name;
	@NotBlank(message="Mô tả không được bỏ trống")
	private String disc;
	@NotBlank(message="Hình ảnh không được bỏ trống")
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@OneToMany(mappedBy="type", fetch=FetchType.EAGER)
	private Collection<Cars> cars;
	
	public Types() {
		
	}
	public Types(String name, String disc) {
		super();
		this.name = name;
		this.disc = disc;
	}
	public Collection<Cars> getCars() {
		return cars;
	}
	public void setCars(Collection<Cars> cars) {
		this.cars = cars;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
