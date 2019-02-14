package members;

public class OrderDetailDO {
	String detail_no;
	String order_no;
	String prod_no;
	Integer sale_price;
	Integer order_qty;
	String cart_detailno;
	String flag;

	public String getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}

	public Integer getSale_price() {
		return sale_price;
	}

	public void setSale_price(Integer sale_price) {
		this.sale_price = sale_price;
	}

	public Integer getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(Integer order_qty) {
		this.order_qty = order_qty;
	}

	public String getCart_detailno() {
		return cart_detailno;
	}

	public void setCart_detailno(String cart_detailno) {
		this.cart_detailno = cart_detailno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
