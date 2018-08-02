package jdbcexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/*
1. work 계정으로 접속한다.
2. 검색하려는 문자(들)을 표준입력 받는다.
3. 해당 문자(들)를 이름에 가지고 있는 직원의 정보를 뷰를 이용하여 다음과 같이 출력한다.(이름 비교시 대소문자 구분 없이)

- 직원이 존재하면
   xxx 직원은 근무중입니다.
   xxxx년 xx월에 입사했고 현재 xx 부서에서 근무하고 있습니다.

- 직원이 존재하지 않면
   xxx 직원은 근무하지 않습니다.

4. 계속 검토하려고 하는지 프롬프트하고 종료하거나 2번부터 반복한다.
 */

public class SearchEmp4 {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "work", "work");
		Scanner sc = new Scanner(System.in);
		ResultSet rs;
		PreparedStatement pstmt = null;		
		
		while(true) {
			System.out.print("검색하려는 문자(들) 입력 > ");
			String name = sc.next().toUpperCase();			
			pstmt = conn.prepareStatement(
				"SELECT ename, to_char(hiredate, 'yyyy\"년\"mm\"월\"') "
				+ "as hiredate, dname "
				+ "FROM empdept "
				+ "WHERE ename like ?");
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					System.out.println(rs.getString("ename") + " 직원은 근무중입니다.");
					System.out.println(rs.getString("hiredate")+"에 입사했고 현재 "+ 
							rs.getString("dname") + "에서 근무하고 있습니다.");
				}while(rs.next());
			} else {
				System.out.println(name + "문자가 이름에 포함된 직원은 근무하지 않습니다.");
			}

			System.out.println("계속하시겠습니까? y/n");
			if(sc.next().equals("n")) {
				System.out.println("종료");
				break;
			}
				
		}
		sc.close();
		rs.close();
		pstmt.close();
		conn.close();
	}
}
