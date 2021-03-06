package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DAO {

	protected Connection conn;
	protected Statement stmt;
	protected ResultSet rs;
	protected PreparedStatement pstmt;

	public void connect() {
		int choi = 2;
		try {
			if (choi == 1) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@192.168.0.18:1521:xe";

				// 1. jdbc driver
				// 2. db connect
				conn = DriverManager.getConnection(url, "javacafe", "javacafe");
				// System.out.println(conn == null ? "connection error!!" : "success!!");

			} else {
				// 3. create statement
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/oracle_javacafe");
				conn = ds.getConnection();
			}
			if (conn != null) {
				System.out.println("connect()");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of connect()

	public void disconnect() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} // end of disconnect()

}
