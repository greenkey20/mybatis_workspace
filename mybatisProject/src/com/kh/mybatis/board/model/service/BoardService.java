package com.kh.mybatis.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

// 2022.2.10(목) 12h25
// interface = 추상메소드만 가지고 있음 vs 구현할 주체는 별도로 있어야 함
// interface라 객체 생성 안 됨 + 부모 자료형으로는 사용할 수 있음
public interface BoardService {
	
	// 게시글 리스트 조회 관련
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
	
	// 2022.2.11(금) 10h20 게시글 상세 조회 관련 메소드 3개
	// 1. 게시글의 조회 수 증가 = UPDATE(DML)문 실행 -> 처리된 행의 개수 int 자료형 반환
	int increaseCount(int boardNo);
	// 2. 해당 게시글 상세 조회	= 해당 게시글 정보 조회 = boardNo으로 SELECT문 실행 -> Board 객체 1개 반환
	Board selectBoard(int boardNo);
	// 3. 해당 게시글에 달린 댓글들을  조회
	ArrayList<Reply> selectReplyList(int boardNo);
	
	// 2022.2.11(금) 15h5 게시글 검색 관련
	int selectSearchCount(HashMap<String, String> map);
	ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi); // 15h45 추가

}
