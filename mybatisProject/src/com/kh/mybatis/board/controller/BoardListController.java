package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;
import com.kh.mybatis.common.template.Pagination;

// 2022.2.10(목) 12h35
/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// paging 처리
		// 게시판/담당 기능(형태)(e.g. 일반 게시판, 파일 목록, 리뷰 목록 등)에 따라 계속 바뀌는 정보
		int listCount = new BoardServiceImpl().selectListCount();
//		System.out.println(listCount); // 2022.2.10(목) 15h5 현재 14 찍힘
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // menubar.jsp로부터 query string으로 받아옴
		int pageLimit = 10;
		int boardLimit = 5;
		
		// 어떤 게시판이든 위 4개 값 및 공식에 의해 구해져서 고정으로 사용되는 정보 -> com.kh.mybatis.common.template.Pagination 클래스에 따로 빼둠
		/*
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; 
		int endPage = startPage + pageLimit - 1;
		// startPage가 11이라서 endPage가 20이 되어야 하는데, maxPage가 11이라면/총 11페이지까지 밖에 없다면, endPage를 maxPage로 변경
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		*/
		// static 메소드니까 생성자 없이 아래와 같이 작성; Connection conn = JDBCTemplate.getConnection() 형식과 동일한 것임
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
//		System.out.println(pi); // 2022.2.10(목) 15h10 현재 PageInfo [listCount=14, currentPage=1, pageLimit=10, boardLimit=5, maxPage=3, startPage=1, endPage=3]
		
		ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
		// 2022.2.10(목) 16h10 초안 작성 마무리 -> 16h40 테스트 완료
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
