package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;

// 2022.2.11(금) 10h15
/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// interface라 객체 생성 안 됨 + 부모 자료형으로는 사용할 수 있음
//		BoardService boardService = new BoardService(); // "cannot instantiate the type BoardService"
		BoardService boardService = new BoardServiceImpl(); // interface를 부모 자료형으로 사용해서 다형성 적용
		
		// 이 controller에서 (처리)해야 하는 일/기능 = 3개 -> 3개 메소드를 만들어야 함
		// 1. 게시글의 조회 수 증가
		int result = boardService.increaseCount(boardNo);
		
		if (result > 0) { // 해당 게시글 조회 수가 증가했다면 vs 증가에 실패한 경우에는 굳이 게시글 상세 조회할 필요 없음
			// 2. 해당 게시글 상세 조회	<- 해당 게시글 정보 조회	
			Board b = boardService.selectBoard(boardNo);
			
			// 2022.2.11(금) 12h45 나의 질문 = 게시글 상세 조회 성공했을 때만 관련 게시글에 달린 댓글을 조회해오는 논리는 어떨까?
			if (b != null) { // 사용자가 (제목)클릭한 게시글의 상세 조회에 성공한 경우 vs 2022.2.11(금) 12h50 나의 질문 = 해당 게시글 1개 조회 실패한 경우 b로 null이 반환되는 것이 맞는가?
				// 3. 해당 게시글에 달린 댓글들을  조회
				ArrayList<Reply> list = boardService.selectReplyList(boardNo);
				
				request.setAttribute("b", b);
				request.setAttribute("list", list);
				
				request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			} else { // 사용자가 (제목)클릭한 게시글의 상세 조회에 실패한 경우
				request.setAttribute("errorMsg", "게시글 상세 조회에 실패했습니다");
				request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
			
		} else { // 게시글 조회 수 증가에 실패한 경우 -> errorPage로 forwarding -> 2022.2.11(금) 11h10 나의 질문 = web2 workspace에서도 해결 못 한 질문이긴 한데, forwarding이 정확히 무엇인가?
			request.setAttribute("errorMsg", "게시글 상세 조회에 실패했습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		// 2022.2.11(금) 12h5 초안 작성 마무리 -> 2022.2.11(금) 12h40 테스트 완료
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
