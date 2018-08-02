package jdbcexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BlobRead {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT imgcontent ic FROM imgtest WHERE name='aaa.jpeg'");
		if(rset.next()) {
			Blob blob = rset.getBlob("ic");
			System.out.println(blob.length());
			InputStream is = blob.getBinaryStream();
			FileOutputStream fo = new FileOutputStream("c:/iotest/aaa2.jpeg");
			int c=0;
			while((c=is.read())!=-1)
				fo.write(c);
			fo.close();
			is.close();
			// 웨일 브라우저로 이미지를 띄운다.
			Runtime.getRuntime().exec("C:\\Program Files (x86)\\Naver\\Naver Whale\\Application\\whale.exe c:/iotest/aaa2.jpeg");
		}else {
			System.out.println("추출된 데이터가 없습니다.");
		}
		rset.close();
		stmt.close();
		conn.close();		
	}
}