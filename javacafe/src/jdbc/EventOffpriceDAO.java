package jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;

import common.DAO;

public class EventOffpriceDAO extends DAO {

	/*
	 * 1_event_name 2_start_date 3_end_date 4_prod_category 5_off_pct 
	 */

	public ArrayList<EventOffpriceDO> selectAll(String Event_name) {

		connect();
		ArrayList<EventOffpriceDO> eventdao = new ArrayList<EventOffpriceDO>();
		EventOffpriceDO eventdo = null;

		try {
			String sql = "select * from event_offprice where event_name=? order by prod_category";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				eventdo = new EventOffpriceDO();

				eventdo.setEvent_name(rs.getString("event_name"));
				eventdo.setStart_date(rs.getString("start_date"));
				eventdo.setEnd_date(rs.getString("end_date"));
				eventdo.setProd_category(rs.getString("prod_category"));
				eventdo.setOff_pct(rs.getInt("off_pct"));
				
				eventdao.add(eventdo);

			}
			System.out.println("selectAll");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return eventdao;

	}


	
	public EventOffpriceDO selectOne(String user_no) {

		connect();
		EventOffpriceDO event = null;

		try {
			// 1.드라이버 로딩, 2.DB연결
			// 3.sql실행
			String sql = "select * from event_offprice where prod_category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				event = new EventOffpriceDO();
				event.setEvent_name(rs.getString("event_name"));
				event.setStart_date(rs.getString("start_date"));
				event.setEnd_date(rs.getString("end_date"));
				event.setProd_category(rs.getString("prod_category"));
				event.setOff_pct(rs.getInt("off_pct"));
				
				System.out.println("selectOne");

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disconnect();

		}
		return event;

	}// end of selectOne
	
	

	public boolean insert(EventOffpriceDO event) {
		connect();

		try {
			String sql = "INSERT INTO Event_offprice VALUES (?,?,?,?,?,?,?,to_date(?,'rrrrmmdd'),nvl(?,sysdate),nvl(?,sysdate),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEvent_name());
			pstmt.setString(2, event.getStart_date());
			pstmt.setString(3, event.getEnd_date());
			pstmt.setString(4, event.getProd_category());
			pstmt.setInt(5, event.getOff_pct());
			

			pstmt.executeUpdate();
			System.out.println(event.getProd_category() + " inserted.");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			disconnect();

		} 
	}// end of insert
	


	
	
	// 수정
		public boolean update(EventOffpriceDO prod) { // void아니고 boolean타입이기때문에 리턴값 넣어줄것
			// boolean result=false;
			try {
				connect();
				String sql = "update events set event_name=?,start_date=?,end_date=?,prod_category=?,off_pct=?"
						+ "  where event_name=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, prod.getEvent_name());
				pstmt.setString(2, prod.getStart_date()); 
				pstmt.setString(3, prod.getEnd_date());
				pstmt.setString(4, prod.getProd_category());
				pstmt.setInt(5, prod.getOff_pct());
				

				int u = pstmt.executeUpdate();
				if (u > 0) { // 수정에 성공했는지 여부 확인
					System.out.println("이벤트 업데이트 성공");
				} else {
					System.out.println("이벤트 업데이트 실패");
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
}
