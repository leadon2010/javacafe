package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class DAO {

	protected Connection conn;
	protected Statement stmt;
	protected ResultSet rs;
	protected PreparedStatement pstmt;

	final static String DB_URL = "jdbc:oracle:thin:@db202112142040_medium?TNS_ADMIN=D:/Dev/Wallet_DB202112142040";
	final static String DB_USER = "javacafe";
	final static String DB_PASSWORD = "H1q2w3e4r5tR";

	public void connect() {
		int choi = 1;
		try {
			if (choi == 1) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";

				conn = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
				// System.out.println(conn == null ? "connection error!!" : "success!!");

			} else if (choi == 2) {
				// 3. create statement
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup("jdbc/oracle_javacafe");
				conn = ds.getConnection();

			} else if (choi == 3) {
				Properties info = new Properties();
				info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
				info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
				info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");

				OracleDataSource ods = new OracleDataSource();
				ods.setURL(DB_URL);
				ods.setConnectionProperties(info);

				conn = (OracleConnection) ods.getConnection();
				System.out.println("dataSource3");

			}

			if (conn != null) {
				System.out.println("connect!!");
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
