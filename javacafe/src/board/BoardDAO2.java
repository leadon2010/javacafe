package board;

import java.sql.*;
import java.util.*;

/**
 * File : BoardDAO.java
 * Desc : 게시판 DAO 클래스
 * @author 김유미
 */
public class BoardDAO2 { 
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	// Oracle 연결정보
	String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@192.168.0.82:1521";
	
	// DB연결 메서드
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 수정된 주소록 내용 갱신을 위한 메서드
	public boolean updateDB(Board board) {
		connect();		
		String sql ="update board set "
		                + " title =?, "
				        + " contents =?, "
				        + " originalFileName =?, "
				        + " uploadedFileName=? "
				   +" where no=?";			 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(1,board.getContents());
			pstmt.setString(3,board.getOriginalFileName());
			pstmt.setString(4,board.getUploadedFileName());
			pstmt.setString(5,board.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 특정 주소록 게시글 삭제 메서드
	public boolean deleteDB(int gb_id) {
		connect();
		
		String sql ="delete from board where department_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 신규 부서 추가 메서드
	public boolean insertDB(Board board) {
		connect();				
		String sql ="insert into board "
			+ " (no,title,contents ,wdate,userid,originalFileName,uploadedFileName) "+
		       " values( (select nvl(max(no),0)+1 from board), ?, ?, sysdate,?,?,?)";		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getTitle());
			pstmt.setString(2,board.getContents());
			pstmt.setString(3, board.getUserid());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(3, board.getUploadedFileName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}

	// 부서 단건 조회
	public Board getDB(int gb_id) {
		connect();		//no,title,contents ,wdate,userid,originalFileName,uploadedFileName
		String sql = " select no,title,contents ,wdate,userid,originalFileName,uploadedFileName"
				    + "  from board  "
				   + "  where no = ? ";
		Board board = new Board();		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gb_id);
			ResultSet rs = pstmt.executeQuery();			
			// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
			rs.next();
			board.setNo(rs.getString("no"));
			board.setTitle(rs.getString("title"));
			board.setContents(rs.getString("contents"));
			board.setWdate(rs.getString("wdate"));
			board.setOriginalFileName(rs.getString("originalFileName"));
			board.setUploadedFileName(rs.getString("uploadedFileName"));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return board;
	}
	
	// 전체 부서 목록을 가져오는 메서드
	public ArrayList<Board> getDBList() {
		connect();
		ArrayList<Board> datas = new ArrayList<Board>();
		
		String sql = "select * from board order by no desc";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getString("no"));
				board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
				board.setWdate(rs.getString("wdate"));
				board.setOriginalFileName(rs.getString("originalFileName"));
				board.setUploadedFileName(rs.getString("uploadedFileName"));		
				datas.add(board);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return datas;
	}
}