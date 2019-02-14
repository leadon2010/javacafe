package members;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class CartDAO extends DAO {

	public ArrayList<CartsDO> selectAll(String p_user_no) {
		connect();
		ArrayList<CartsDO> clist = new ArrayList<CartsDO>();
		CartsDO cdo = null;
		try {
			String sql = "select user_no, detail_no, prod_no, sales_price, order_qty, get_prod_name(prod_no) p_name from cart_details where user_no = ? order by prod_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_user_no);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cdo = new CartsDO();
				cdo.setUser_no(rs.getString("user_no"));
				cdo.setDetail_no(rs.getString("detail_no"));
				cdo.setProd_no(rs.getString("prod_no"));
				cdo.setSales_price(rs.getInt("sales_price"));
				cdo.setOrder_qty(rs.getInt("order_qty"));
				cdo.setProd_name(rs.getString("p_name"));
				clist.add(cdo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return clist;
	}// end of selectAll

	// public HashMap<String, Object> insertProc(CartsDO bean) throws Exception {
	public boolean insertProc(CartsDO bean) throws Exception {
		CallableStatement cstmt = null;
		// int nextId = 0;
		try {
			connect();
			conn.setAutoCommit(false); // 트랜잭션 처리
			// 1.p_user_no, 2.p_prod_no, 3.p_qty, 4.p_price, 5.p_ret, 6.p_msg
			cstmt = conn.prepareCall("{call create_carts(?,?,?,?,?,?)}");
			cstmt.setString(1, bean.getUser_no());
			cstmt.setString(2, bean.getProd_no());
			cstmt.setInt(3, bean.getOrder_qty());
			cstmt.setInt(4, bean.getSales_price());
			cstmt.registerOutParameter(5, java.sql.Types.NUMERIC);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);

			cstmt.executeUpdate();
			conn.commit(); // 커밋
			// nextId = cstmt.getInt(4);
			// int out_ret = cstmt.getInt(5);
			// String out_msg = cstmt.getString(6);
			// System.out.println(out_msg);
			// HashMap<String, Object> map = new HashMap<String, Object>();
			// map.put("id", nextId);
			// map.put("name", bean.getName());
			// map.put(("content"), bean.getContent());
			// map.put("user_no", bean.getUser_no());
			// return map;
			System.out.println("insertProc");
			return true;
		} catch (Throwable e) {
			try {
				conn.rollback(); // 롤백
				return false;
			} catch (SQLException ex) {
			}
			throw new Exception(e.getMessage());
		} finally {
			disconnect();
		}
	}// end of insertProc
}
