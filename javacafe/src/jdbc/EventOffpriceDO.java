package jdbc;

public class EventOffpriceDO {

	private static String event_name;
	private String start_date;
	private String end_date;
	private String prod_category;
	private Integer off_pct;
	
	
	public static String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getProd_category() {
		return prod_category;
	}
	public void setProd_category(String prod_category) {
		this.prod_category = prod_category;
	}
	public Integer getOff_pct() {
		return off_pct;
	}
	public void setOff_pct(Integer off_pct) {
		this.off_pct = off_pct;
	}
	
	
	@Override
	public String toString() {
		return "event_offprice [event_name=" + event_name + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", prod_category=" + prod_category + ", off_pct=" + off_pct + "]";
	}
	
	
	
	
}
