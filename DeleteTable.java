package jdbcexam;

import java.sql.*;

// product 테이블에서 balance가 argument 이하인 레코드를 삭제
public class DeleteTable {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		PreparedStatement pstmt = conn.prepareStatement("DELETE from product WHERE balance<=?");
		pstmt.setInt(1,Integer.parseInt(args[0]));
		int del_su = pstmt.executeUpdate();
		System.out.println("재고가 " + args[0] + " 이하인 상품 정보를 " + del_su + "개 삭제하였습니다.");
		pstmt.close();
		conn.close();
	}

}
