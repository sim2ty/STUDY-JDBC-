package jdbcexam;

import java.sql.*;

// Run Configuration으로 입력받아서 Table 생성/삭제
// create Argument 줄 경우에는 Create Table 실행.
// 첫 줄에 공백 있을 경우에는 error
// 여러 줄을 Argument로 줄 경우에는 else문 실행. (단, 기존 테이블이 없으면 error)
public class CreateTable {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		if(args.length==1 && args[0].equals("create")) {
			stmt.executeUpdate("CREATE TABLE product " + "(id char(5), classid char(2), name varchar(50), balance int, price float)");
			System.out.println("테이블이 생성되었습니다.");
		}else {
			stmt.executeUpdate("DROP TABLE product");
			System.out.println("테이블이 삭제되었습니다.");
		}
		stmt.close();
		conn.close();
	}
}
