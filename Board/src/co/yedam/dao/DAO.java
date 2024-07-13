package co.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * DB connection 기능. 
 */
public class DAO {

	Connection conn = null;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	
	public Connection getConn()	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";	// MyBatis에서 사용함
		String user = "board03";
		String pass = "board03";
		
		
		try {
			Class.forName(driver); /* 1 */ // 오라클 드라이버 체크
			conn = DriverManager.getConnection(url, user, pass); /* 2 */
		} catch (SQLException /* 2 */ | ClassNotFoundException e /* 1 */) {
			System.out.println("오라클 드라이버 없음");
			e.printStackTrace();
		}
		return conn;
	}// end of getConn.

}
