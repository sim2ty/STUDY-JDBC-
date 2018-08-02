package jdbcexam;

import java.sql.*;

class SelectVisitor {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name, writedate, memo " + "from visitor order by writedate" );
		while(rs.next()) {	// select된 결과가 없으면 비어있는 ResultSet 객체 return
			//출력방법 1 (가독성 좋음)
			System.out.println(rs.getString("name") + "  ");
			System.out.println(rs.getString("writedate") + "  ");
			System.out.println(rs.getString("memo") + "  ");
			//출력방법 2
			System.out.println(rs.getString(1) + "  ");
			System.out.println(rs.getString(2) + "  ");
			System.out.println(rs.getString(3) + "  ");
		}
		rs.close();		// 권장
		stmt.close();	// 권장
		conn.close();	// 필수
	}
}
