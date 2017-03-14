package remind.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
public class User {
	@Id
	@GeneratedValue
	Long id;
	@Column(unique = true, name = "userId", nullable = false)
	String userId;
	@Column(name = "password", nullable = false)
	String password;
	@Column(name = "name", nullable = false)
	String name;
	@Column(name = "email", nullable = false)
	String email;

	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public void update(User user){
		this.name = user.name;
		this.email = user.email;
		
	}
	
	public Boolean matchPassword(String password){
		if(this.password ==null){			
			return false;
		}
		return this.password.equals(password);
	}

	
	@Override
	public String toString() {
		return userId;
	}
	
}

