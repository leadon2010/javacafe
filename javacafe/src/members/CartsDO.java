package members;

import jdbc.GoodsDO;

public class CartsDO extends GoodsDO {

	String detail_no;
	String user_no;
	String prod_no;
	Integer sales_price;
	Integer order_qty;

	public String getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}

	public Integer getSales_price() {
		return sales_price;
	}

	public void setSales_price(Integer sales_price) {
		this.sales_price = sales_price;
	}

	public Integer getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(Integer order_qty) {
		this.order_qty = order_qty;
	}

}
