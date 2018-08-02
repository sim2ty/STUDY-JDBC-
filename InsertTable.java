package jdbcexam;

import java.sql.*;

public class InsertTable {
	public static void main(String[] args) throws Exception{
		try{
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스에 접속. 적절한 JDBC URL 설정.
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT into product values('00001','10','자바 프로그래밍',50,16500)");
			stmt.executeUpdate("INSERT into product values('00002','10','J2EE 구현 패턴',40,12000)");
			stmt.executeUpdate("INSERT into product values('00003','10','JSP 2.0',60,18000)");
			System.out.println("데이터들을 추가하였습니다.");
			// JDBC는 자동 commit
			stmt.close();
			conn.close();	
		} catch (SQLException e) {
			System.out.println("오류 발생 : " + e);
		}		
	}
}
