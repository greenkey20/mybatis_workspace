package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 2022.2.9(수) 11h40
public class Template {
	
	/* 기존 JDBC
	 * public static Connection getConnection() {
	 * 	// driver.properties 파일 읽어들임
	 *	// 해당 db와 접속된 Connection 객체 생성해서 return
	 * }
	 * 
	 * public static void close(JDBC용 객체) {
	 * 	// 전달받은 JDBC용 객체(자원)를 반납시키는 구문
	 * }
	 * 
	 * public static void commit/rollback(Connection 객체) {
	 * 	// 트랜잭션 처리
	 * }
	 */
	
	// MyBatis
	public static SqlSession getSqlSession() {
		// mybatis-config.xml 파일 읽어들임 -> 해당 db와 접속된 SqlSession 객체 생성해서 return/반환
		SqlSession sqlSession = null;
		
		// SqlSession 객체를 생성하기 위해서는 SqlSessionFactory 객체가 필요 <- SqlSessionFactory 객체 생성을 위해서는 SqlSessionFactoryBuilder 객체가 필요
		String resource = "/mybatis-config.xml"; // 문자열 가장 앞의 / = 모든 source folder의 최상위 폴더들을 의미 -> 이 경우에는 resources, src -> 11h50 나의 질문 = 강사님 필기의 'src'는 무엇인가? -> 나의 관찰 = src도 source folder였음
		
		try {
			InputStream stream = Resources.getResourceAsStream(resource); // 자원으로부터 통로를 얻어냄 + 외부 자원을 못 찾을 경우에 io exception이 발생할 수 있는 바, 예외 처리함
			
			// 단계1) new SqlSessionFactoryBuilder() -> SqlSessionFactoryBuilder 객체 생성
			// 단계2) .build(stream) -> 통로로부터 mybatis-config.xml 파일을 읽어들여서 sqlSessionFactory 객체 생성
			// 단계3) .openSession(false) -> SqlSession 객체 생성 + 앞으로 트랜잭션 처리 시 자동으로 commit (안)할 것인지 여부를 지정; false = openSession() 기본 값 = 자동 commit을 하지 않겠다 = 개발자가 직접 commit을 하겠다
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	} // getSqlSession() 종료

}
