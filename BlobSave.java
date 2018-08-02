package jdbcexam;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BlobSave {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		File file = new File("c:/iotest/aaa.jpeg");
		InputStream is = new FileInputStream(file);
		PreparedStatement pstmt = conn.prepareStatement("INSERT into imgtest values(?, ?)");
		pstmt.setString(1, "aaa.jpeg");
		pstmt.setBinaryStream(2, is, file.length());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		System.out.println("저장 성공!");
	}
}