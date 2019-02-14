package members;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import common.DAO;
import jdbc.GoodsDO;

public class OrdersDAO extends DAO {

	// 해당 유저의 전체 주문과 주문의 세부정보를 가지고 온다.
	public ArrayList<OrderSets> selectOrdersDetails(String p_user_no) {
		connect();
		ArrayList<OrderSets> oslist = null;
		ArrayList<OrderDetailDO> oddlist = null;
		OrdersDO od = null;
		OrderDetailDO odd = null;
		OrderSets oss = null;

		try {
			String sql1 = "select * from orders where user_no = ? and NVL(deliver_flag, 'N') = 'Y' order by order_no desc";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, p_user_no);
			ResultSet rs = pstmt.executeQuery();
			oslist = new ArrayList<OrderSets>();

			while (rs.next()) {
				oss = new OrderSets();
				od = new OrdersDO();
				od.setOrder_no(rs.getString("order_no"));
				od.setUser_no(rs.getString("user_no"));
				od.setOrder_date(rs.getString("order_date"));
				od.setDelever_price(rs.getString("delever_price"));
				od.setDeliver_addr(rs.getString("deliver_addr"));
				od.setDelever_reg(rs.getString("delever_reg"));

				String sql2 = "select * from oe_details where order_no = ? order by detail_no";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, rs.getString("order_no"));
				ResultSet rss = pstmt.executeQuery();
				oddlist = new ArrayList<OrderDetailDO>();
				while (rss.next()) {

					odd = new OrderDetailDO();
					odd.setDetail_no(rss.getString("detail_no"));
					odd.setOrder_no(rss.getString("order_no"));
					odd.setProd_no(rss.getString("prod_no"));
					odd.setSale_price(rss.getInt("sale_price"));
					odd.setOrder_qty(rss.getInt("order_qty"));
					odd.setCart_detailno(rss.getString("cart_detailno"));
					odd.setFlag(rss.getString("flag"));
					oddlist.add(odd);

				}
				oss.setOrd(od);
				oss.setOdlist(oddlist);

				oslist.add(oss);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return oslist;
	}// end of selectOrdersDetails

	// 해당 유저의 주문정보를 가지고 와서 주문상세내역을 하단에 보여준다.
	public List<HashMap<String, Object>> selectOrderDetails(String p_user_no) {
		connect();
		List<HashMap<String, Object>> olist = new ArrayList<HashMap<String, Object>>();
		try {
			String sql = "SELECT od.order_no, od.user_no, ot.detail_no, ot.prod_no, ot.sale_price, ot.order_qty"
					+ " FROM orders od JOIN   oe_details ot ON (od.order_no = ot.order_no)"
					+ " WHERE od.deliver_flag IS NOT NULL AND od.user_no = ? ORDER BY od.order_no,ot.detail_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_user_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> omap = new HashMap<String, Object>();
				omap.put(("order_no"), rs.getString("order_no"));
				omap.put("user_no", rs.getString("user_no"));
				omap.put("detail_no", rs.getString("detail_no"));
				omap.put("prod_no", rs.getString("prod_no"));
				omap.put("sale_price", rs.getInt("sale_price"));
				omap.put("order_qty", rs.getInt("order_qty"));
				// System.out.println(rs.getString("order_no"));
				olist.add(omap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return olist;
	}// end of selectOrderDetails

	// 사용자의 주문헤더정보
	public List<HashMap<String, Object>> selectOrder(String p_user_no, String p_order_no) {
		connect();
		List<HashMap<String, Object>> olist = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> omap = null;
		try {
			String sql = "select * from orders where user_no = ? and order_no = NVL(?, order_no) and NVL(deliver_flag ,'N') = 'N' order by order_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_user_no);
			pstmt.setString(2, p_order_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				omap = new HashMap<String, Object>();
				omap.put("user_no", rs.getString("user_no"));
				omap.put("order_no", rs.getString("order_no"));
				omap.put("deliver_addr", rs.getString("deliver_addr"));
				omap.put("order_date", rs.getDate("order_date"));
				omap.put("delever_reg", rs.getString("delever_reg"));
				System.out.println(rs.getString("order_no"));
				olist.add(omap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return olist;
	}

	// 주문별 상세내역을 보여준다.
	public List<HashMap<String, Object>> selectAll(String p_order_no) {
		connect();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			String sql = "select detail_no, order_no, prod_no, sale_price, order_qty, cart_detailno, flag, get_prod_name(prod_no) as prod_name "
					+ "from oe_details where order_no = ? order by detail_no ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_order_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// detail_no order_no prod_no sale_price order_qty cart_detailno flag
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("detail_no", rs.getString("detail_no"));
				map.put("order_no", rs.getString("order_no"));
				map.put("prod_no", rs.getString("prod_no"));
				map.put("sale_price", rs.getString("sale_price"));
				map.put("order_qty", rs.getString("order_qty"));
				map.put("cart_detailno", rs.getString("cart_detailno"));
				map.put("flag", rs.getString("flag"));
				map.put("prod_name", rs.getString("prod_name"));
				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return list;
	}

	public String orderComplete(OrdersDO p_orders) {
		connect();
		CallableStatement cstmt = null;
		String p_ret = null;
		try {
			conn.setAutoCommit(false);
			cstmt = conn.prepareCall("{call orderproc(?,?,?,?,?)}");
			cstmt.setString(1, p_orders.getOrder_no());
			cstmt.setString(2, p_orders.getDelever_price());
			cstmt.setString(3, p_orders.getDeliver_addr());
			cstmt.setString(4, p_orders.getDelever_reg());
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			conn.commit(); // 커밋
			p_ret = cstmt.getString(5);
			System.out.println("orderComplete " + p_ret);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return p_ret;
	}// end of orderComplete

	public String createOrderHeader(String p_user_no, GoodsDO gds) {
		connect();
		String orderHeaderNo = null;
		// 헤더정보 하나 생성하고 그 헤더번호를 받아와서 라인정보에서 넣어줄때 사용.
		CallableStatement cstmt = null;
		try {
			connect();
			conn.setAutoCommit(false); // 트랜잭션 처리
			cstmt = conn.prepareCall("{call order_header(?,?)}");
			cstmt.setString(1, p_user_no);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			conn.commit(); // 커밋
			orderHeaderNo = cstmt.getString(2);

			// System.out.println(detailNo + " " + prodNo + salesPrice + " "+orderQty);
			String sql = "INSERT INTO oe_details (detail_no, order_no, prod_no, sale_price, order_qty, cart_detailno, flag)"
					+ " values(ORDERS_SEQ.Nextval ,?,?,?,?,?,'N')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderHeaderNo);
			pstmt.setString(2, gds.getProd_no());
			pstmt.setInt(3, gds.getProd_price());
			pstmt.setInt(4, gds.getOnhand_qty());
			pstmt.setString(5, "0");
			int rtn = pstmt.executeUpdate();
			System.out.println(rtn + " order insert.");

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return orderHeaderNo;
	}

	// 장바구니에 담겨 있는 내역을 주문하기버튼을 눌렀을 때 실행하는 내용.
	// 해당 유저의 신규주문을 생성하고 장바구니에 있는 것을 주문에 담는다.
	public String createOrderHeader(String p_user_no, String[] p_cart_no) {
		connect();
		String orderHeaderNo = null;
		// 헤더정보 하나 생성하고 그 헤더번호를 받아와서 라인정보에서 넣어줄때 사용.
		CallableStatement cstmt = null;
		try {
			connect();
			conn.setAutoCommit(false); // 트랜잭션 처리
			cstmt = conn.prepareCall("{call order_header(?,?)}");
			cstmt.setString(1, p_user_no);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			conn.commit(); // 커밋
			orderHeaderNo = cstmt.getString(2);

			for (int i = 0; i < p_cart_no.length; i++) {
				// for cart에 들어있는 cart_no 를 찾아서 값을 가져와서 넣어준다.
				String carts = "select detail_no, user_no, prod_no, sales_price, order_qty from cart_details where user_no = ? and detail_no = ? ";
				pstmt = conn.prepareStatement(carts);
				pstmt.setString(1, p_user_no);
				pstmt.setString(2, p_cart_no[i]);
				ResultSet rs = pstmt.executeQuery();
				String detailNo = null;
				String prodNo = null;
				Integer salesPrice = null;
				Integer orderQty = null;
				if (rs.next()) {
					detailNo = rs.getString("detail_no");
					prodNo = rs.getString("prod_no");
					salesPrice = rs.getInt("sales_price");
					orderQty = rs.getInt("order_qty");
					// System.out.println(detailNo + " " + prodNo + salesPrice + " "+orderQty);
					String sql = "INSERT INTO oe_details (detail_no, order_no, prod_no, sale_price, order_qty, cart_detailno, flag)"
							+ " values(ORDERS_SEQ.Nextval ,?,?,?,?,?,'N')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, orderHeaderNo);
					pstmt.setString(2, prodNo);
					pstmt.setInt(3, salesPrice);
					pstmt.setInt(4, orderQty);
					pstmt.setString(5, detailNo);
					int rtn = pstmt.executeUpdate();
					System.out.println(rtn + " order insert.");
				}
			}
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return orderHeaderNo;

	}// end of createHeaderOrder()
}
