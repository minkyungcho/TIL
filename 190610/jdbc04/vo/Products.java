package vo;

import java.sql.Date;

public class Products {
	
	int pd_no;
	String pd_name;
	String pd_sub_name;
	String fact_no;
	Date pd_date;
	int pd_cost;
	int pd_price;
	int pd_amount;
	
	
	
	public Products() {
	}


	public Products(int pd_no, String pd_name, String pd_sub_name, String fact_no, Date pd_date, int pd_cost,
			int pd_price, int pd_amount) {
		this.pd_no = pd_no;
		this.pd_name = pd_name;
		this.pd_sub_name = pd_sub_name;
		this.fact_no = fact_no;
		this.pd_date = pd_date;
		this.pd_cost = pd_cost;
		this.pd_price = pd_price;
		this.pd_amount = pd_amount;
	}


	public Products(int pd_no, String pd_name, String pd_sub_name, String fact_no, int pd_cost,
			int pd_price, int pd_amount) {
		this.pd_no = pd_no;
		this.pd_name = pd_name;
		this.pd_sub_name = pd_sub_name;
		this.fact_no = fact_no;
		this.pd_cost = pd_cost;
		this.pd_price = pd_price;
		this.pd_amount = pd_amount;
	}


	public int getPd_no() {
		return pd_no;
	}


	public void setPd_no(int pd_no) {
		this.pd_no = pd_no;
	}


	public String getPd_name() {
		return pd_name;
	}


	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}


	public String getPd_sub_name() {
		return pd_sub_name;
	}


	public void setPd_sub_name(String pd_sub_name) {
		this.pd_sub_name = pd_sub_name;
	}


	public String getFact_no() {
		return fact_no;
	}


	public void setFact_no(String fact_no) {
		this.fact_no = fact_no;
	}


	public Date getPd_date() {
		return pd_date;
	}


	public void setPd_date(Date pd_date) {
		this.pd_date = pd_date;
	}


	public int getPd_cost() {
		return pd_cost;
	}


	public void setPd_cost(int pd_cost) {
		this.pd_cost = pd_cost;
	}


	public int getPd_price() {
		return pd_price;
	}


	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}


	public int getPd_amount() {
		return pd_amount;
	}


	public void setPd_amount(int pd_amount) {
		this.pd_amount = pd_amount;
	}


	@Override
	public String toString() {
		return "Products [pd_no=" + pd_no + ", pd_name=" + pd_name + ", pd_sub_name=" + pd_sub_name + ", fact_no="
				+ fact_no + ", pd_date=" + pd_date + ", pd_cost=" + pd_cost + ", pd_price=" + pd_price + ", pd_amount="
				+ pd_amount + "]";
	}
	
	
	
}
