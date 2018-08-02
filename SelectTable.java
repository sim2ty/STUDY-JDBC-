package jdbcexam;

import java.sql.*;

class SelectTable {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from product");
		while(rs.next()) {	
			System.out.println(rs.getString("id") + "  ");
			System.out.println(rs.getString("classid") + "  ");
			System.out.println(rs.getString("name") + "  ");
			System.out.println(rs.getString("balance") + "  ");
			System.out.println(rs.getString("price") + "  ");	
		}
		rs.close();		
		stmt.close();	
		conn.close();	
	}
}
