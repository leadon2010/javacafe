package members;

public class OrdersDO {
	String order_no;
	String user_no;
	String order_date;
	String delever_price;
	String deliver_addr;
	String delever_reg;
	String[] cart_detailno;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getDelever_price() {
		return delever_price;
	}

	public void setDelever_price(String delever_price) {
		this.delever_price = delever_price;
	}

	public String getDeliver_addr() {
		return deliver_addr;
	}

	public void setDeliver_addr(String deliver_addr) {
		this.deliver_addr = deliver_addr;
	}

	public String getDelever_reg() {
		return delever_reg;
	}

	public void setDelever_reg(String delever_reg) {
		this.delever_reg = delever_reg;
	}

	public String[] getCart_no() {
		return cart_detailno;
	}

	public void setCart_no(String[] cart_no) {
		this.cart_detailno = cart_no;
	}

}
