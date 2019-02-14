package members;

import java.util.ArrayList;

public class OrderSets {

	private OrdersDO ord;
	private ArrayList<OrderDetailDO> odlist = new ArrayList<OrderDetailDO>();

	public OrdersDO getOrd() {
		return ord;
	}

	public void setOrd(OrdersDO ord) {
		this.ord = ord;
	}

	public ArrayList<OrderDetailDO> getOdlist() {
		return odlist;
	}

	public void setOdlist(ArrayList<OrderDetailDO> odlist) {
		this.odlist = odlist;
	}

}
