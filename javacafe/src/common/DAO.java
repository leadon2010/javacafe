package common;

import java.sql.Connection;
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
		try {
			// 1. jdbc driver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. db connect
			// String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// conn = DriverManager.getConnection(url, "hr", "hr");
			// System.out.println(conn == null ? "connection error!!" : "success!!");

			// 3. create statement
			// stmt = conn.createStatement();
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/oracle_jsp");
			conn = ds.getConnection();
			if (conn != null) {
				//System.out.println("connect()");
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
