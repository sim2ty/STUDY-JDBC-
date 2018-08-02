package jdbcexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

/*
작성 클래스명 : SelectEmp
접속 오라클 계정 : work

1. work 계정으로 접속한다.
2. true 와 false 랜덤값을 추출한다.
3. true 이면
   emp 테이블에서 모든 직원들의 이름과 월급, 두 개의 컬럼을 추출한다.
   다음형식으로 표준 출력한다.

   XXX 직원의 월급은 xx,xxx원입니다. 
   XXX 직원의 월급은 xx,xxx원입니다.
   XXX 직원의 월급은 xx,xxx원입니다.
         :
4. false 이면
   emp 테이블에서 모든 직원들의 이름과 입사 날짜, 두 개의 컬럼을 추출한다.
   다음형식으로 표준 출력한다.

   XXX 직원은 xxxx년 xx월 xx일에 입사하였습니다. 
   XXX 직원은 xxxx년 xx월 xx일에 입사하였습니다. 
   XXX 직원은 xxxx년 xx월 xx일에 입사하였습니다. 
         :
*/

public class SelectEmp {
	public static void main(String[] args) throws Exception{
		Random ran = new Random();
		boolean flag = ran.nextBoolean();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@70.12.114.98:1521:XE", "work", "work");
		Statement stmt = conn.createStatement();

		if(flag) {
			ResultSet rs1 = stmt.executeQuery("SELECT ename, to_char(sal,'9,999') as 월급 from emp");
			while(rs1.next()) {	
				System.out.println(rs1.getString("ename")+" 직원의 월급은 "+rs1.getString("월급")+"원 입니다.");						
			}
			rs1.close();
		}else {
			ResultSet rs2 = stmt.executeQuery("SELECT ename, to_char(hiredate,'yyyy')||'년 '||to_char(hiredate,'mm')||'월'||to_char(hiredate,'dd')||'일' as 날짜 from emp");
			while(rs2.next()) {	
				System.out.println(rs2.getString("ename")+" 직원은 "+rs2.getString("날짜")+"에 입사하였습니다.");	
			}
			rs2.close();
		}				
		stmt.close();	
		conn.close();	
	}
}
