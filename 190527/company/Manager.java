package company;

public class Manager extends Employee{
	private double incentive;
	
	public Manager() {
	} // default constructor

	public Manager(String id, String name, double salary, String dept, double incentive) {
		super(id, name, salary, dept); // Employee의 constructor 이용
		// TODO Auto-generated constructor stub
//		setId(id);
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dept = dept;
		this.incentive = incentive; // + incentive
	}
	
	
//	@Override
//	public String toString() {
//		return "Manager [getId()=" + getId() + ", getName()=" + getName() + ", getSalary()=" + getSalary()
//				+ ", getDept()=" + getDept() + ", incentive=" + incentive + "]";
//	}
	
	
	// 재정의
	// Override
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", salary=" + salary + ", dept=" + dept + ", incentive="
				+ incentive + "]";
	}

	@Override
	public double salaryM() {
		double money = 0;
		money = super.salaryM() + this.incentive;
		return money;
	}

//	@Override
//	public double annSalary() {
//		double money = 0;
//		money = 12 * this.salaryM();
//		return money;
//	} // employee의 annSalary와 똑같아서 override 할 필요 없음.
	
	
	
}
