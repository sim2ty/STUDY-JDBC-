package jdbcexam;

import java.sql.*;

class ConnectDB {
	public static void main(String[] args) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// JDBC URL -> jdbc:oracle:thin:@localhost:1521:XE
			//			   (jdbc:DB서버이름:드라이버정보:드라이버에게전달할내용)
			// 어떤 DB 서버를 어떤 JDBC 드라이버를 사용하여 접속할 것인지를 알려주는 규격화된 문자열
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "jdbctest", "jdbctest");
			DatabaseMetaData meta = conn.getMetaData();

			System.out.println("Database : " + meta.getDatabaseProductName());
			System.out.println("version " + meta.getDatabaseProductVersion());
			System.out.println("User name : " + meta.getUserName());
			conn.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
