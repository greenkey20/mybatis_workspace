package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

// 2022.2.9(수) 15h15
public class MemberServiceImpl implements MemberService { // interface를 상속받는데 interface에서 추상메소드로 만들어 놓은 것들을 여기서 구현 안 해서 클래스명에 빨간줄 뜸 -> 'add unimplemented methods'

	private MemberDao memberDao = new MemberDao(); // MemberDao 클래스 자주 사용할 것 같으니까, 필드로 만들어둠
	
	// 'override' = annotation = interface에 만들어놓은 추상메소드들을 overriding하는 것
	@Override
	public int insertMember(Member m) {
		
		// 기존 JDBC 방식
		/*
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn); // 지금까지 학생들의 질문 = Connection 객체를 왜 service 클래스에서 close하는가? -> 지금까지 강사님께서 설명하실 수 있었던 이유 =  
		
		return result;
		*/
		
		// 앞으로는 MyBatis에서 제공하는 sqlSession을 가지고 함
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = memberDao.insertMember(sqlSession, m); // insert의 처리 결과 = 0(실패 시) 또는 1(성공 시)
		
		if (result > 0) {
			sqlSession.commit();
		}
		/*
		else { // else = 'result = 0' = db에 뭔가 들어간 것/insert된 데이터가 없음 -> rollback할 필요 없음
			   // 지금까지 수업하며 rollback이 꼭 필요했던 경우 = sql문을 1개 이상 보낼 때 e.g. 첨부파일 upload/insert 실패한 경우, 그에 앞서 게시글 insert한 것 rollback해야 함; 게시글 조회수 count 올려놨는데, 어떤 사유로(악의적인 목적으로 게시글이 망가진? 경우) 게시글 조회 실패한 경우, 게시글 조회수 increaseCount rollback해야 함
			// sqlSession.rollback();
		}
		*/
		
		sqlSession.close();
		
		return result;
	} // 2022.2.9(수) 16h40 insertMember() 종료

	// 2022.2.10(목) 10h15
	@Override
	public Member loginMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginUser = memberDao.loginMember(sqlSession, m); // id 및 비밀번호 일치하는 활성 회원 정보 1행 ou null
		
		// SELECT문 처리인 바, 트랜잭션 처리 필요 없음
		
		sqlSession.close();
		
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		return 0;
	}

	@Override
	public int deleteMember(Member m) {
		return 0;
	}

}
