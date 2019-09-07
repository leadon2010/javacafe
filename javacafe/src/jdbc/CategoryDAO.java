package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class CategoryDAO extends DAO {

	public void updateCategory(CategoryDO cat) {
		connect();
		String sql = "update category set category_name=?, category_desc=? where category_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(3, cat.getCategory_id());
			pstmt.setString(1, cat.getCategory_name());
			pstmt.setString(2, cat.getCategory_desc());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}

	public void insertCategory(CategoryDO cat) {
		connect();
		String sql = "insert into category values(?,?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cat.getCategory_id());
			pstmt.setString(2, cat.getCategory_name());
			pstmt.setString(3, cat.getCategory_desc());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}

	public ArrayList<CategoryDO> selectAll() {
		connect();

		ArrayList<CategoryDO> ctrl = new ArrayList<CategoryDO>();
		CategoryDO ctr = null;

		try {
			String sql = "select * from category order by category_id";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ctr = new CategoryDO();

				ctr.setCategory_id(rs.getString("category_id"));
				ctr.setCategory_name(rs.getString("category_name"));
				ctr.setCategory_desc(rs.getString("category_desc"));

				ctrl.add(ctr);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return ctrl;

	}// end of selectAll

}
