package company;

public class Employee {
//	private String id;
	protected String id; // 하위 클래스까지는 공유 가능
//	private String name;
	protected String name;
//	private double salary;
	protected double salary;
//	private String dept;
	protected String dept;
	public Employee() {
	}
	public Employee(String id, String name, double salary, String dept) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", dept=" + dept + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public double salaryM() {
		double money = 0;
		double temp = 0;
		// 4대 보험료 8.4% 공제
		// 세금 3.2# 공제
		temp += this.salary * 0.084;
		temp += this.salary * 0.032;
		money = this.salary - temp;
		
		return money;
	}
	
	public double annSalary() {
		double money = 0;
		money = 12 * this.salaryM();
		return money;
	}
	
}
