package members;

import java.sql.ResultSet;
import java.util.ArrayList;

import common.DAO;

public class CategoryDAO extends DAO {

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
