package jdbcexam;

import java.sql.*;

// product 테이블에서 arguments로 입력된 특정 ID를 갖는 레코드의 balance를 100으로 수정
public class UpdateTable{
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		PreparedStatement pstmt = conn.prepareStatement("UPDATE product SET balance=100 WHERE id=?");
//		PreparedStatement pstmt = conn.prepareStatement("UPDATE product SET balance=100 WHERE trim(id)=?");
		pstmt.setString(1, args[0]);
		int update_su = pstmt.executeUpdate();
		System.out.println("수정된 레코드 수 : " + update_su);
		System.out.println("상품번호가 " + args[0] + "인 상품의 balance 필드 수정완료");
		pstmt.close();
		conn.close();
	}

}
