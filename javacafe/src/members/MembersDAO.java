package members;

import java.sql.ResultSet;
import java.util.ArrayList;

import common.DAO;

public class MembersDAO extends DAO {

	/*
	 * 1_user_no 2_password 3_name 4_email 5_phone 6_address1 7_address2 8_birth
	 * 9_reg_date 10_out_date 11_grade
	 */

	public ArrayList<MembersDO> selectAll() {

		connect();
		ArrayList<MembersDO> usrdao = new ArrayList<MembersDO>();
		MembersDO usrdo = null;

		try {
			String sql = "select * from members order by user_no";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				usrdo = new MembersDO();

				usrdo.setUser_no(rs.getString("user_no"));
				usrdo.setPassword(rs.getString("password"));
				usrdo.setName(rs.getString("name"));
				usrdo.setEmail(rs.getString("email"));
				usrdo.setPhone(rs.getString("phone"));
				usrdo.setAddress1(rs.getString("address1"));
				usrdo.setAddress2(rs.getString("address2"));
				usrdo.setBirth(rs.getString("birth"));
				usrdo.setReg_date(rs.getString("reg_date"));
				usrdo.setOut_date(rs.getString("out_date"));
				usrdo.setGrade(rs.getString("grade"));

				usrdao.add(usrdo);

			}
			System.out.println("selectAll");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return usrdao;

	}

	public MembersDO login(String usrno, String usrpw) {
		connect();
		MembersDO usr = null;

		try {
			String sql = "select user_no, password, name, grade from members where user_no=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usrno);
			pstmt.setString(2, usrpw);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("login true" + rs.getString("grade"));
				usr = new MembersDO();
				usr.setUser_no(rs.getString("user_no"));
				usr.setPassword(rs.getString("password"));
				usr.setGrade(rs.getString("grade"));
				usr.setName(rs.getString("name"));

			} else {
				System.out.println("login false");

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return usr;

	}// end of login

	public boolean update(MembersDO mbr) {
		connect();
		try {
			String sql = "update members set password=?, name=?, email=?, phone=?, address1=?, grade=? "
					+ "where user_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr.getPassword());
			pstmt.setString(2, mbr.getName());
			pstmt.setString(3, mbr.getEmail());
			pstmt.setString(4, mbr.getPhone());
			pstmt.setString(5, mbr.getAddress1());
			//pstmt.setString(6, mbr.getBirth());
			//pstmt.setString(7, mbr.getOut_date());
			pstmt.setString(6, mbr.getGrade());
			pstmt.setString(7, mbr.getUser_no());
			int r = pstmt.executeUpdate();
			System.out.println("회원정보가 변경완료" + r + " 건 처리.");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			disconnect();

		}
	}// end of update

	public boolean insert(MembersDO usr) {
		connect();

		try {
			String sql = "INSERT INTO members VALUES (?,?,?,?,?,?,?,to_date(?,'rrrrmmdd'),nvl(?,sysdate),nvl(?,sysdate),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usr.getUser_no());
			pstmt.setString(2, usr.getPassword());
			pstmt.setString(3, usr.getName());
			pstmt.setString(4, usr.getEmail());
			pstmt.setString(5, usr.getPhone());
			pstmt.setString(6, usr.getAddress1());
			pstmt.setString(7, usr.getAddress2());
			pstmt.setString(8, usr.getBirth());
			// pstmt.setString(9, usr.getReg_date());
			pstmt.setString(9, "");
			// pstmt.setString(10, usr.getOut_date());
			pstmt.setString(10, "");
			pstmt.setString(11, usr.getGrade());

			// pstmt.setString(6, emp.getSalary());

			pstmt.executeUpdate();
			System.out.println(usr.getUser_no() + " inserted.");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			disconnect();

		}
	}// end of insert

	public MembersDO selectOne(String user_no) {

		connect();
		MembersDO usr = null;

		try {
			// 1.단계: 드라이버 로딩, 2.단계: DB연결
			// 3.단계 sql실행
			String sql = "select * from members where user_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				usr = new MembersDO();
				usr.setUser_no(rs.getString("user_no"));
				usr.setPassword(rs.getString("password"));
				usr.setName(rs.getString("name"));
				usr.setEmail(rs.getString("email"));
				usr.setPhone(rs.getString("phone"));
				usr.setAddress1(rs.getString("address1"));
				usr.setAddress2(rs.getString("address2"));
				usr.setBirth(rs.getString("birth"));
				usr.setGrade(rs.getString("grade"));

				System.out.println("selectOne");

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return usr;

	}// end of selectOne
}
