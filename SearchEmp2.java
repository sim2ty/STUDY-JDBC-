package jdbcexam;

import java.sql.*;
import java.util.Scanner;

/*	<Statement 객체로 구현>
1. work 계정으로 접속한다.
2. 검색하려는 직원의 이름을 표준입력 받는다.
3. 해당 직원의 정보를 다음과 같이 출력한다.(이름 비교시 대소문자 구분 없이)

- 직원이 존재하면
   xxx 직원은 근무중입니다.
   xxxx년 xx월에 입사했고 현재 xx 부서에서 근무하고 있습니다.

- 직원이 존재하지 않면
   xxx 직원은 근무하지 않습니다.

4. 계속 검토하려고 하는지 프롬프트하고 종료하거나 2번부터 반복한다.
*/

public class SearchEmp2 {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "work", "work");
		Scanner scn = new Scanner(System.in);
		System.out.print("검색하려는 직원명을 입력하세요 > ");
		String input = scn.next();
		String text = input.toLowerCase();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ename,to_char(hiredate,'yyyy\"년 \"mm\"월\"') 입사시기,deptno from emp WHERE lower(ename)='"+text+"'");
		if(rs.next()) {
			do {
				System.out.println(rs.getString("ename") + " 직원은 근무중입니다.");
				System.out.print(rs.getString("입사시기") + "에 입사했고 현재 ");
				System.out.println(rs.getString("deptno") + "부서에서 근무하고 있습니다.");
			}while(rs.next());
			
		}else {
			System.out.println(text + " 직원은 근무하지 않습니다.");
		}
	}

}
