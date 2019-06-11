package vo;

public class Factory {
	
	String fact_no;
	String fac_name;
	String fac_loc;
	public Factory() {
	}
	public Factory(String fact_no, String fac_name, String fac_loc) {
		this.fact_no = fact_no;
		this.fac_name = fac_name;
		this.fac_loc = fac_loc;
	}
	public String getFact_no() {
		return fact_no;
	}
	public void setFact_no(String fact_no) {
		this.fact_no = fact_no;
	}
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getFac_loc() {
		return fac_loc;
	}
	public void setFac_loc(String fac_loc) {
		this.fac_loc = fac_loc;
	}
	@Override
	public String toString() {
		return "Factory [fact_no=" + fact_no + ", fac_name=" + fac_name + ", fac_loc=" + fac_loc + "]";
	}
}
