package jdbcexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
작성 클래스명 : InputProduct
접속 오라클 계정 : jdbctest

1. jdbctest 계정으로 접속한다.
2. product 테이블에 저장할 데이터를 사용자에게 입력 받는다.
    상품 ID를 입력하세요 :
    상품의 CLASSID를 입력하세요 :
    상품명을 입력하세요 :
    상품 가격을 입력하세요 :
    상품 재고량을 입력하세요 :   
3. 입력받은 데이터를 product 테이블에 저장한다.
4. 저장하는 동안 오류 발생시 다시 입력하라는 메시지를 출력하고 
    입력받는것 부터 다시 시작하게 한다.
5. 저장하는 동안 오류가 발생하지 않으면 
    저장된 내용을 추출하여 화면에 출력하고 종료한다.
6. 저장이 완료되면 " 저장 성공" 이라는 메시지를 출력하고 종료한다.


*/

public class InputProduct {
	public static void main(String[] args) throws Exception{
		Scanner scn = new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "jdbctest", "jdbctest");
		Statement stmt = conn.createStatement();
		
		while(true) {
		System.out.print("상품 ID를 입력하세요 : ");
		String id = scn.next();
		System.out.print("상품의 CLASSID를 입력하세요 : ");
		String classid = scn.next();
		System.out.print("상품명을 입력하세요 : ");
		String name = scn.next();
		System.out.print("상품 재고량을 입력하세요 : ");
		int balance = scn.nextInt();
		System.out.print("상품 가격을 입력하세요 : ");
		float price = scn.nextFloat();
		
		try{
			// 문자열의 경우에는 ' ' 필수. 문자열이 아닌 경우에는 없어도 됨.
			stmt.executeUpdate("INSERT into product values('"+id+"', '"+classid+"', '"+name+"', "+balance+", "+price+")");
			ResultSet rs = stmt.executeQuery("SELECT * from product");
			while(rs.next()) {
				System.out.print(rs.getString("id") + "\t\t");
				System.out.print(rs.getString("classid") + "\t\t");
				System.out.print(rs.getString("name") + "\t\t");
				System.out.print(rs.getString("balance") + "\t\t");
				System.out.println(rs.getString("price") + "\t\t");				
			}
			rs.close();
			break;
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("다시 입력하세요.");
			continue;
		}finally {
			try{
				if(stmt != null) stmt.close();
		        if(conn != null) conn.close();
			}catch (Exception e) {}				
		}
		}
	}
}
