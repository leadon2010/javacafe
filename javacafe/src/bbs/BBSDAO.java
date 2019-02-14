package bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import common.DAO;
import bbs.BBSDAO;
 
public class BBSDAO extends DAO {
		
	// 단건조회
	public BBS selectOne(String i) {
		BBS bbs = null;
		try {
			// 1.드라이버 로딩 2.DB연결
			connect();
			// 3.SQL 구분 실행
			stmt = conn.createStatement();
			String sql = " select Title,Contents,user_no, bbsnum, Reg_Date, Ref, Re_step, ReadCount, Password_yn, Ref_lev from boards where bbsnum = " +i;  
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bbs = new BBS();
				bbs.setBbsnum(rs.getString("BBSNUM"));
				bbs.setTitle(rs.getString("Title"));
				bbs.setContents(rs.getString("Contents"));
				bbs.setRef(rs.getString("Ref"));
				bbs.setRe_step(rs.getString("Re_step"));
				bbs.setReg_date(rs.getDate("Reg_Date"));
				bbs.setReadcount(rs.getString("ReadCount"));
				bbs.setPassword_yn(rs.getString("Password_yn"));
				bbs.setRef_lev(rs.getString("Ref_lev"));
				bbs.setUser_no(rs.getString("user_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5 연결해제
			disconnect();
		}
		return bbs;
	}

	// 전체 조회
	public List<BBS> selectAll() {
		ArrayList<BBS> list = new ArrayList<BBS>();
		BBS bbs = null;
		try {
			// 1.드라이버 로딩 2.DB연결
			connect();
			// 3.SQL 구분 실행
			stmt = conn.createStatement(); 
			String sql = " select * from boards order by bbsnum ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bbs = new BBS();
				bbs.setBbsnum(rs.getString("BBSNUM"));
				bbs.setTitle(rs.getString("Title"));
				bbs.setContents(rs.getString("Contents"));
				bbs.setRef(rs.getString("Ref"));
				bbs.setRe_step(rs.getString("Re_step"));
				bbs.setReg_date(rs.getDate("Reg_Date"));
				bbs.setReadcount(rs.getString("ReadCount"));
				bbs.setPassword_yn(rs.getString("Password_yn"));
				bbs.setRef_lev(rs.getString("Ref_lev"));
				bbs.setUser_no(rs.getString("user_no"));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5 연결해제
			disconnect();
		}
		return list;
	}

	// 한 페이지 데이터 조회 

	public List<BBS> selectPage(int start, int end, String prod_no) {
		ArrayList<BBS> list = new ArrayList<BBS>();
		BBS bbs = null; 
		try {
			// 1.드라이버 로딩 2.DB연결
			connect();
			// 3.SQL 구분 실행
			stmt = conn.createStatement();
			String sql = "select b.*  from ( select rownum rn, a.* from ( "+
			"select * from boards where nvl(prod_no,'x')=nvl(?,nvl(prod_no,'x')) ORDER BY ref, re_step "
			 + ") a  ) b  where  rn between ? and ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prod_no);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bbs = new BBS();
				bbs.setBbsnum(rs.getString("BBSNUM"));
				bbs.setTitle(rs.getString("Title"));
				bbs.setContents(rs.getString("Contents"));
				bbs.setRef(rs.getString("Ref"));
				bbs.setRe_step(rs.getString("Re_step"));
				bbs.setReg_date(rs.getDate("Reg_Date"));
				bbs.setReadcount(rs.getString("ReadCount"));
				bbs.setPassword_yn(rs.getString("Password_yn"));
				bbs.setRef_lev(rs.getString("Ref_lev"));
				bbs.setUser_no(rs.getString("user_no"));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5 연결해제 
			disconnect();
		}
		return list;
	}
	
	//등록구현
	public boolean insert(BBS bbs) {
		
		try {
			connect();
			String sql = "insert into boards (bbsnum, title, contents, ref, user_no,reg_date,readcount, re_step, password_yn,prod_no)"				
					+ " values(BOARDS_SEQ.nextval, ?, ?, Boards_seq.currval, ? ,sysdate,0,0,?,?)";
			
			String bbsnum = bbs.getBbsnum();				
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, bbs.getBbsnum());				
			pstmt.setString(1, bbs.getTitle());
			pstmt.setString(2, bbs.getContents());	
			pstmt.setString(3, bbs.getUser_no());
			pstmt.setString(4, bbs.getPassword_yn());
			pstmt.setString(5, bbs.getProd_no());
			
			
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 업데이트 완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
			
	//답글 등록

	public boolean insertReply(BBS bbs) {
		
		try {
			connect();
			String ref = bbs.getRef();     // 원본글 번호(그룹번호)
	        String re_step = bbs.getRe_step();     // 답변글의 순서
	      
	      
	            //conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
	            
	            // ref(그룹번호)와 seq(답글순서)을 확인하여 원본 글에 다른 답변 글이 있으면, 
	            // 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.            
	            
	        	//답글 업데이트?
	        
				String sql = "update boards set re_step = re_step+1 where ref = ? and re_step > ?";

				pstmt = conn.prepareStatement(sql);		
	            pstmt.setString(1, ref);
	            pstmt.setString(2, re_step);
	            
	            pstmt.executeUpdate();	  
			sql = "insert into boards (bbsnum, title, contents, ref, user_no, reg_date,readcount, re_step, ref_lev)"				
					+ " values(BOARDS_SEQ.nextval, ?, ?, ?, ? ,sysdate,0,?,?)";
							
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, bbs.getBbsnum());				
			pstmt.setString(1, bbs.getTitle());
			pstmt.setString(2, bbs.getContents());	
			pstmt.setString(3, bbs.getRef());	
			pstmt.setString(4, bbs.getUser_no());
			pstmt.setString(5, bbs.getRe_step());
			pstmt.setString(6, bbs.getRef_lev());
			
						
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 업데이트 완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}	

	public boolean delete(String bbsnum) {
		// 삭제 구현
		try {
			connect();
			
			String sql = "delete from boards where bbsnum = "+ bbsnum;
			stmt = conn.createStatement();			
			int r = stmt.executeUpdate(sql);	
			
			System.out.println(r + "건이 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	// 수정 구현
	public boolean update(BBS bbs) {		
		
		try {
			connect();
			String sql = "update boards set title = ?, contents=? where bbsnum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbs.getTitle());
			pstmt.setString(2, bbs.getContents());
			pstmt.setString(3, bbs.getBbsnum());					
	
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 업데이트 완료");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	//조회수	
	public boolean ReadcountUpdate(String num) {
		try {
			connect();
			String sql = "update boards set readcount = readcount+1 where bbsnum = ?";

			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, num);
			pstmt.executeUpdate();			

		} catch (Exception e) {
			e.printStackTrace();		
			return false;
		} finally {
			disconnect(); 
		}	
			return true;
	}
	
	//전체레코드 건수 조회
	public int count(String prod_no) {
		int result = 0;
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "select count(*) from boards where nvl(prod_no,'x')=nvl('"+prod_no+"',nvl(prod_no,'x'))";		
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}		
		return result ;
	}	
	
	//답글 업데이트
	public boolean updateRef(BBS bbs)
    {        
        String ref = bbs.getRef();     // 원본글 번호(그룹번호)
        String re_step = bbs.getRe_step();     // 답변글의 순서
        
        try {               
            connect();
            //conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            // ref(그룹번호)와 seq(답글순서)을 확인하여 원본 글에 다른 답변 글이 있으면, 
            // 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.            
            
            connect();
			String sql = "update boards set re_step = re_step+1 where ref = ? and re_step > ?";

			pstmt = conn.prepareStatement(sql);		
            pstmt.setString(1, ref);
            pstmt.setString(2, re_step);
            
            pstmt.executeUpdate();	  
          
    } catch (Exception e) {
		e.printStackTrace();		
		return false;
	} finally {
		disconnect(); 
	}	
		return true;
}
		
	
/*
		 * //전체조회 테스트 List<Employees> datas = empDAO.selectAll();
		 * System.out.println(datas); //이름만 모두 출력; for(Employees temp : datas) {
		 * System.out.println(temp.getFirst_name() + " " + temp.getLast_name()); }
		 * 
		 * Employees emp = empDAO.selectOne("100"); System.out.println(emp);
		 */
		// empDAO.delete(300);
		// empDAO.insert (new Employees("350", "째" , "완", "s1s1s134@nddr.com", null,
		// "ST_MAN"));

		/*
		 * emp = new Employees (); emp.setFirst_name("이이이"); emp.setLast_name("하이");
		 * emp.setEmployee_id("350"); empDAO.update(emp);
		 */

	} // end of main
