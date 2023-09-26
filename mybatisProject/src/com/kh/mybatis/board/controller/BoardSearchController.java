package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;
import com.kh.mybatis.common.template.Pagination;

// 2022.2.11(금) 14h30
/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 '검색' 버튼 click 시 요청 url = /mybatis/search.bo?currentPage=1&condition=writer&keyword=ad
		// 검색한 뒤, 검색 결과 paging bar에서 '[2]' click 시 요청 url = /mybatis/search.bo?currentPage=2&condition=writer&keyword=ad
		// cf. a 태그 사용 시, 또는 onclick 속성 값으로 요청 보낼 때, query string으로 요청 보냄 vs form 태그로 보낼 때는 명시적으로 url로 보내지 않음 + form 태그 내부에 있는 값들이 key(name 속성의 값)-value(value 속성의 값) 세트로 전달됨
		String condition = request.getParameter("condition"); // writer 또는 title 또는 content
		String keyword = request.getParameter("keyword"); // 사용자가 입력한/검색하고자 하는 (검색)keyword 값
		
		HashMap<String, String> map = new HashMap<>(); // HashMap = key-value 세트들이 들어있는 주머니/보따리 vs ArrayList에 담아서 보내면 mapper.xml에서 값을 뽑을 방법이 없음(강사님께서 어떤 학우님의 질문에 대해 답변해주심 15h12)
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		BoardService boardService = new BoardServiceImpl();
		
		int searchCount = boardService.selectSearchCount(map); // 현재 검색 결과에 맞는 게시글의 총 개수를 db로부터 조회 <- 검색 조건 및 사용자가 입력한 keyword를 map에 담아서 전달
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // number format exception = 99% parsing 관련해서 발생한 오류 -> view단에서 넘겨준 key 값과 여기서 접근하려고 한 key 값이 맞는지 확인해보기 등
		int pageLimit = 10; // 게시판 목록 조회 시 기획/결정한 것
		int boardLimit = 5; // 게시판 목록 조회 시 기획/결정한 것
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
//		System.out.println(pi);
		// e.g. 2022.2.11(금) 15h30 작성자 'ad' 검색 시, PageInfo [listCount=7, currentPage=1, pageLimit=10, boardLimit=5, maxPage=2, startPage=1, endPage=2] 찍힘
		// e.g. 2022.2.11(금) 15h30 제목 '다' 검색 시, PageInfo [listCount=6, currentPage=1, pageLimit=10, boardLimit=5, maxPage=2, startPage=1, endPage=2] 찍힘
		// e.g. 2022.2.11(금) 15h30 내용 '안녕' 검색 시, PageInfo [listCount=5, currentPage=1, pageLimit=10, boardLimit=5, maxPage=1, startPage=1, endPage=1] 찍힘
		
		// 검색 결과는 없을 수도 있고, 1개일 수도 있고, 여러 개일 수도 있는데, ArrayList<Board>에 담아 오고자 함 + db 조회 시 map 및 pi에 담긴 정보들 필요한 바, 넘겨줌
		ArrayList<Board> list = boardService.selectSearchList(map, pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		// 응답 페이지에서 사용하기 위해 아래 2개의 값도 request 객체의 attribute 영역에 set해서 함께 넘겨줌
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
		// 2022.2.11(금) 16h25 초안 작성 마무리 -> 17h40 테스트 완료
		
		// cf. 2022.2.1(화) 17h40 semi-project TravelSpotSearchKeywordController 테스트하며 나의 관찰
		// 이슈1) 특정 filter로 검색 -> 상세 조회 -> 아래 버튼으로 '목록으로 돌아가기'하면 filter 검색(x) 전체 조회(o) 결과가 나옴
		// -> travelSpotDetailView.jsp 하단 버튼 중 <a href="<%= contextPath %>/tsList.ads?category=<%= category %>&currentPage=1&boardLimit=7" class="btn btn-primary btn-sm">목록으로 돌아가기</a>를 history.back()으로 교체함
		// -> <button onclick="history.back();" class="btn btn-sm btn-secondary">목록으로 돌아가기</button>으로 교체했더니, 업체 상세 조회 -> 업체 정보 수정 -> 이 '목록으로 돌아가기' 버튼 누르면 '업체 정보 수정' 페이지로 돌아감 = 이상함
		// -> 다시 a 태그 버튼으로 되돌려둠 = 특정 filter로 검색 -> 상세 조회 -> filter 검색 결과를 다시 보려면 '목록으로 돌아가기' 버튼 사용하지 말고, 브라우저 '뒤로 가기' 화살표 눌러야 함
		// 이슈2) 이 서블릿에서 paging 처리해서 넘겼을 때, 특정 filter로 검색한 결과의 2페이지를 보다가 1페이지로 넘어가면 filter 검색(x) 전체 조회(o) 결과가 나옴 -> 일단 filter 검색에는 paging 의미 없도록 이 서블릿에서 boardLimit을 100으로 설정해둠 + 이슈1과 관련 있는 듯?
		// 이슈3) 특정 filter로 검색한 결과 목록(list A)을 보다가 다른 filter로 검색하려고 하면, 전체 목록(x) list A(o) 중에서 검색이 되는 듯 함
		// 이슈4) 특정 filter로 검색한 결과 목록을 보다가 이 category 여행지 전체 목록을 다시 보고 싶을 경우 어떻게 해야 할지 모르겠음 + 지금으로써는 뒤로 가기(단, '뒤로 가기'하면 내가 선택했던 filter 및 검색 keyword가 남아있어 내가 정확히 어떤 목록을 보고 있는 것인지 헷갈림)
		// 2022.2.11(금) 17h50 강사님께 이슈4 관련해서 여쭤보니, 이런 기능 별로 필요 없을 듯 <- 전체 목록 보고 싶으면 '게시판' 눌러서 다시 가거나, 뒤로 가거나 할 듯.. 정 필요하면 '전체 보기' 버튼 만드는 것도 방법이 될 수 있고..
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
