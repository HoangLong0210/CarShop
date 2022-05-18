package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="AD")
public class Admin {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message="username empty!")
	private String username;
	@NotBlank(message="password empty!")
	private String pass;
	@NotBlank(message="Email không được bỏ trống")
	@Pattern(regexp="^[a-z][a-z0-9]+@[a-z]+(\\.[a-z]{2,4}){1,2}$", message="Email gồm chữ cái thường và chữ số, kí tự đầu tiên phải là chữ cái")
	private String email;
	
	@OneToMany(mappedBy="admin", fetch=FetchType.EAGER)
	private Collection<Orders> orders;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
