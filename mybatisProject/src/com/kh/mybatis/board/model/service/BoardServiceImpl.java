package com.kh.mybatis.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

import static com.kh.mybatis.common.template.Template.*;

// 2022.2.10(목) 12h30
public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao = new BoardDao();
	
	// 2022.2.10(목) 14h30
	@Override
	public int selectListCount() {
		SqlSession sqlSession = /* Template.*/getSqlSession();
		
		int listCount = boardDao.selectListCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	} // selectCount() 종료

	// 2022.2.10(목) 15h15
	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<Board> list = boardDao.selectList(sqlSession, pi);
		
		sqlSession.close();
		
		return list;
	} // 16h5 selectList() 종료

	// 2022.2.11(금) 10h30
	@Override
	public int increaseCount(int boardNo) {
		SqlSession sqlSession = getSqlSession();
		
		int result = boardDao.increaseCount(sqlSession, boardNo);
		
		if (result > 0) {
			sqlSession.commit();
		}
		// 이 메소드 내에서 게시글 조회도 같이 했다면 게시글 조회 실패 시 rollback 필요 vs 이 경우에는 rollback 굳이 안 해줘도 됨
		
		sqlSession.close();
		
		return result;
	} // increaseCount() 종료 11h5

	// 2022.2.11(금) 11h10
	@Override
	public Board selectBoard(int boardNo) {
		SqlSession sqlSession = getSqlSession();
		
		Board b = boardDao.selectBoard(sqlSession, boardNo);
		
		sqlSession.close();
		
		return b;
	} // selectBoard() 종료

	// 2022.2.11(금) 11h30
	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<Reply> list = boardDao.selectReplyList(sqlSession, boardNo);
		
		sqlSession.close();
		
		return list;
	} // selectReplyList() 종료

	// 2022.2.11(금) 15h10
	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		SqlSession sqlSession = getSqlSession();
		
		int searchCount = boardDao.selectSearchCount(sqlSession, map);
		
		// select문 처리 -> 트랜잭션 처리 필요 없음
		sqlSession.close();
		
		return searchCount;
	} // selectSearchCount() 종료

	// 2022.2.11(금) 15h45
	@Override
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) { // 메소드 선언부에서/메소드가 호출 당할 때 필요한 매개변수 = parameter
		SqlSession sqlSession = getSqlSession();
		
		ArrayList<Board> list = boardDao.selectSearchList(sqlSession, map, pi); // 메소드 호출/사용 시 넣는 전달 값/인자 = argument
		
		sqlSession.close();
		
		return list;
	} // selectSearchList() 종료

}
