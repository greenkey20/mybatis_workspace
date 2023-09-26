package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

//2022.2.10(목) 12h30
public class BoardDao {
	
	// 2022.2.10(목) 14h35
	public int selectListCount(SqlSession sqlSession) {
		// 'db 조회 결과 = 1행' + 쿼리문 채울 필요 없음/완성된 쿼리문 날림 -> selectOne(String arg0) 메소드 사용
		return sqlSession.selectOne("boardMapper.selectListCount");
	} // selectListCount() 종료
	
	// 2022.2.10(목) 15h15
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		// db 조회 결과를 list로 받고자 하므로 selectList() 메소드 사용
//		return sqlSession.selectList("boardMapper.selectList", pi); // 단, paging 처리를 위해 이 메소드 사용할 수 없음
		
		// MyBatis에서는 paging 처리를 위해 RowBounds라는 클래스 제공 -> limit과 offset 필요로 함
		/* e.g. listCount = 14, boardLimit = 5인 경우,
		 * offset = 몇 개의 게시글을 건너뛰고 조회할 것인지에 대한 값		offset(건너뛸 숫자/게시글 개수)		(board)limit(조회할 숫자)
		 * currentPage = 1이면, 1~5번째 글을 게시글 목록에 보여줌			0							5
		 * currentPage = 2이면, 6~10번째 글을 게시글 목록에 보여줌			5							5
		 * currentPage = 3이면, 11~14번째 글을 게시글 목록에 보여줌			10							5
		 * ..
		 * -> offset = (currentPage - 1) * boardLimit
		 */
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		// RowBounds 객체를 넘겨야 할 경우, selectList() 메소드의 오버로딩된 형태로 매개변수가 3개인 메소드를 사용해야 함
		
		// (오버로딩된 형태 3개 모두)selectList() 메소드의 반환 자료형 = list -> ArrayList로 강제 형 변환 필요 + 단, 이 때 <Board> 제네릭 지정해주면 오류 나는 바, 안 씀
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds); // 내가 이 기능 처리를 위해 날릴 쿼리문은 완성되어있음/채울 부분 없음 -> selectList() 메소드의 2번째 매개변수로 전달할 것/값도, 전달할 필요도 없음 -> null 전달/넘김
	} // 16h5 selectList() 종료
	
	// 2022.2.11(금) 10h35
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		// '내가 실행하고자 하는 sql문 = UPDATE' + sql문 완성시키기 위해 넘길 값이 있음 -> sqlSession 객체의, 매개변수 2개 받는 update() 메소드 사용 
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	} // increaseCount() 종료 10h50
	
	// 2022.2.11(금) 11h10
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		// 게시글 번호로 조회하므로, 조회 결과는 1행인 ResultSet + 미완성된 sql문을 완성시키기 위해 값을 넘겨야 함 -> 매개변수 2개 받는 selectOne() 메소드 사용
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	} // selectBoard() 종료 11h25
	
	// 2022.2.11(금) 11h35
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
	} // selectReplyList() 종료 12h5
	
	// 2022.2.11(금) 15h10
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
	} // selectSearchCount() 종료 15h25
	
	// 2022.2.11(금) 16h
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi) {
		// paging 처리는 RowBounds 객체에게 위임 -> RowBounds 객체는 인자로써 아래 2가지 값이 필요 -> 16h25 나의 질문 = RowBounds 클래스에 대해 공식 문서 등 더 읽어보고 싶다 -> https://mybatis.org/mybatis-3/apidocs/reference/org/apache/ibatis/session/RowBounds.html
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
	} // selectSearchList() 종료 16h25

}
