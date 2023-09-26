package com.kh.mybatis.member.model.dao;

import java.sql.PreparedStatement;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

// 2022.2.9(수) 15h30
public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, Member m) {
		// 기존 JDBC 방식
		/*
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			result = pstmt.executeUpdate();
		} catch(xx) {
			예외 처리
		} finally {
			close(pstmt);
		}
		
		return result;
		*/
		
		// sqlSession.sql문 종류에 맞는 메소드("mapper 파일의 namespace.내가 실행하고자 하는 해당 sql문의 식별자/id", sql문을 완성시킬 객체(해당 sql문이 미완성 상태가 아니라면, sql문을 완성시킬 객체는 생략 가능)) -> sqlSession에서 제공하는 (아주 많은)메소드를 통해서 sql문을 (sqlSession이 알아서)찾아서 실행하고, 결과를 바로 받아볼 수 있음
		// 16h15 나의 질문 = 강사님 설명은 'mybatis-config.xml' 파일 mappers 태그에 올려둔 것 중에 알아서 찾는다고 하셨는데, mapper 파일의 namespace와 어떻게 연결(?)되는가?
		return sqlSession.insert("memberMapper.insertMember", m); // insert의 처리 결과 = 0(실패 시) 또는 1(성공 시)
	} // 2022.2.9(수) 16h30 insertMember() 종료
	
	// 2022.2.10(목) 10h20
	public Member loginMember(SqlSession sqlSession, Member m) {
		// SELECT문 관련 sqlSession 객체에 select() 메소드가 여러 가지 있지만, selectOne(), selectList() 잘 알아두기
		// selectOne() = 조회 결과가 1행일 경우 e.g. userId는 unique 제약조건이 걸려있는 바, 해당 userId 및 userPwd로 조회하면, 조회 결과 = 1행 vs 조회 결과가 없다면 null 반환
		
		// 방법1) Member loginUser 변수 선언해서 사용
//		Member loginUser = sqlSession.selectOne("memberMapper.loginMember", m);
//		return loginUser;
		
		// 방법2) 1줄로 작성
		return sqlSession.selectOne("memberMapper.loginMember", m); // namespace가 'memberMapper'인 mapper.xml 파일상 id가 'loginMember'인 쿼리문 실행 + 그 쿼리문을 완성시키기 위해 객체 m을 전달해야 함
	} // loginMember() 종료

}
