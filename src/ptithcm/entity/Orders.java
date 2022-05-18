package ptithcm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Orders")
public class Orders {
	@Id
	@GeneratedValue
	private int id;
	@Generated( value = GenerationTime.ALWAYS )
	private String oid;
	@NotBlank(message="Tên khách hàng không được bỏ trống")
	@Pattern(regexp="[\\p{L}-]+", message="Tên chỉ bao gồm kí tự a-zA-Z")    // regex for unicode O._.O
	private String customer;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date datebuy;
	private int stat;
	@NotBlank(message="Email không được bỏ trống")
	@Pattern(regexp="^[a-z][a-z0-9]+@[a-z]+(\\.[a-z]{2,4}){1,2}$", message="Email gồm chữ cái thường và chữ số, kí tự đầu tiên phải là chữ cái")
	private String email;
	@NotBlank(message="SĐT không được bỏ trống")
	@Pattern(regexp="^[0][0-9]{9}$", message="SĐT phải bao gồm 10 chữ số và số 0 ở đầu")
	private String phone;
	@NotBlank(message="Địa chỉ không được bỏ trống")
	private String addres;
	@Min(value=1, message="Số lượng mua ít nhất là 1")
	private int amount;
	private long total;
	
	@ManyToOne
	@JoinColumn(name="carId")
	private Cars car;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Orders() {
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public  String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDatebuy() {
		return datebuy;
	}

	public void setDatebuy(Date datebuy) {
		this.datebuy = datebuy;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}
	
	
}
