package goods;

import java.sql.ResultSet;
import java.util.ArrayList;

import common.DAO;
import jdbc.CartsDO;

public class GoodsDAO extends DAO {

	// getInstance

	// 단건 조회
	public GoodsDO selectOne(String prod_no) {
		GoodsDO prod = null;
		try {
			// 1. 드라이버 로딩 2.DB연결
			connect();
			// 3. SQL 구문 실행
			stmt = conn.createStatement();
			String sql = "select * from goods where prod_no = '" + prod_no + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				prod = new GoodsDO();
				prod.setProd_no(rs.getString("prod_no"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setOnhand_qty(rs.getInt("onhand_qty"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setOff_price(rs.getInt("off_price"));
				prod.setProd_category(rs.getString("prod_category"));
				prod.setProd_image(rs.getString("prod_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 5. 연결 해제
			disconnect();
		}
		return prod;
	}

	// 전체 조회
	public ArrayList<GoodsDO> selectAll() {

		GoodsDO prod = new GoodsDO();
		ArrayList<GoodsDO> list = new ArrayList<GoodsDO>();

		try {
			connect();

			stmt = conn.createStatement(); // createStatement는 DB로 SQL문을 보내기 위한 개체
			String sql = "select * from goods order by prod_no";
			ResultSet rs = stmt.executeQuery(sql); // sql 쿼리 실행
			while (rs.next()) {
				prod = new GoodsDO();
				prod.setProd_no(rs.getString("prod_no"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setOnhand_qty(rs.getInt("onhand_qty"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setOff_price(rs.getInt("off_price"));
				prod.setProd_category(rs.getString("prod_category"));
				prod.setProd_image(rs.getString("prod_image"));

				list.add(prod);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 카테고리 조회
	public ArrayList<GoodsDO> selectAll(String category) {

		GoodsDO prod = new GoodsDO();
		ArrayList<GoodsDO> list = new ArrayList<GoodsDO>();

		try {
			connect();

			stmt = conn.createStatement(); // createStatement는 DB로 SQL문을 보내기 위한 개체
			String sql = "select * from goods where prod_category = nvl('" + category
					+ "', prod_category) order by prod_no";
			// System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql); // sql 쿼리 실행
			while (rs.next()) {
				prod = new GoodsDO();
				prod.setProd_no(rs.getString("prod_no"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setOnhand_qty(rs.getInt("onhand_qty"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setOff_price(rs.getInt("off_price"));
				prod.setProd_category(rs.getString("prod_category"));
				prod.setProd_image(rs.getString("prod_image"));

				list.add(prod);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 카테고리 메뉴 클릭시 분류
	public ArrayList<GoodsDO> selectCategory() {

		GoodsDO prod = new GoodsDO();
		ArrayList<GoodsDO> list = new ArrayList<GoodsDO>();

		try {
			connect();

			stmt = conn.createStatement();
			String sql = "select * from goods where prod_category = nvl('',prod_category)";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new GoodsDO();
				prod.setProd_no(rs.getString("prod_no"));
				prod.setProd_name(rs.getString("prod_name"));
				prod.setProd_content(rs.getString("prod_content"));
				prod.setOnhand_qty(rs.getInt("onhand_qty"));
				prod.setProd_price(rs.getInt("prod_price"));
				prod.setOff_price(rs.getInt("off_price"));
				prod.setProd_category(rs.getString("prod_category"));
				prod.setProd_image(rs.getString("prod_image"));

				list.add(prod);
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 수정
	public boolean update(GoodsDO prod) { // void아니고 boolean타입이기때문에 리턴값 넣어줄것
		// boolean result=false;
		try {
			connect();
			String sql = "update goods set prod_name=NVL(?,prod_name),prod_content=NVL(?,prod_content),onhand_qty=NVL(?,onhand_qty)"
					+ " ,prod_price=NVL(?,prod_price),off_price=NVL(?,off_price),useyn=NVL(?,useyn),prod_image=NVL(?,prod_image) "
					+ "  where prod_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, prod.getProd_name());
			pstmt.setString(2, prod.getProd_content());
			pstmt.setInt(3, prod.getOnhand_qty());
			pstmt.setInt(4, prod.getProd_price());
			pstmt.setInt(5, prod.getOff_price());
			pstmt.setString(6, prod.getUseyn());
			pstmt.setString(7, prod.getProd_image());
			pstmt.setString(8, prod.getProd_no());

			int u = pstmt.executeUpdate();
			if (u > 0) { // 수정에 성공했는지 여부 확인
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
				return false;
				// histoty.go(-1);
			}
			System.out.println(u + "건 수정 완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}

	public String createProdNo(String category) {
		connect();
		String newpo = null;
		try {
			String sql = "select create_prod_no(?) as pno from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			newpo = rs.getString("pno");
			System.out.println("신규번호: " + newpo);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return newpo;

	}// end of createProdNo

	// 상품 등록
	public boolean insert(GoodsDO prod) {
		try {
			GoodsDAO newg = new GoodsDAO();
			String newpno = newg.createProdNo(prod.getProd_category());

			connect();
			String sql = "insert into goods(prod_name,prod_content,onhand_qty,prod_price,off_price,prod_category,prod_image,prod_no,useyn) "
					+ "values (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prod.getProd_name());
			pstmt.setString(2, prod.getProd_content());
			pstmt.setInt(3, prod.getOnhand_qty());
			pstmt.setInt(4, prod.getProd_price());
			pstmt.setInt(5, prod.getOff_price());
			pstmt.setString(6, prod.getProd_category());
			pstmt.setString(7, prod.getProd_image());
			pstmt.setString(8, newpno);
			pstmt.setString(9, prod.getUseyn());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 등록완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	/*
	 * // 장바구니로 이동
	 */
	public boolean insert(CartsDO cart) {
		try {
			GoodsDAO newg = new GoodsDAO();
			String newpno = newg.createProdNo(cart.getProd_no());

			connect();
			String sql = "insert into goods(user_no,prod_no,p_qty,p_price) values (?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getUser_no());
			pstmt.setString(2, cart.getProd_no());
//			pstmt.setInt(3, cart.getP_qty());
//			pstmt.setInt(4, cart.getP_price());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 등록완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	// 삭제
	public void delete(int prod_no) {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "delete from goods where prod_no= " + prod_no;
			int r = stmt.executeUpdate(sql);
			System.out.println(r + "건 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
