package ser;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String pwd;
	private int age;
	public User() {
		super();
	}
	public User(String id, String pwd, int age) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", age=" + age + "]";
	}
	
}
