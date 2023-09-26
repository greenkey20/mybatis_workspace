package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.service.MemberServiceImpl;
import com.kh.mybatis.member.model.vo.Member;

// 2022.2.9(수) 14h40
/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 요청받았으므로, 단계0) encoding
		request.setCharacterEncoding("UTF-8");
		
		// 단계1) request 객체로부터 값 뽑기
		String userId = request.getParameter("userId"); // getParameter() 반환형 = String
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email"); // 선택적 입력 사항 -> 반환형 = String 또는 ""
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// 2개 이상의 입력 값/데이터는 어딘가(2022.2.10(목) 9h35 강사님 보충 설명 = dao에서 sqlSession의  CRUD 처리 메소드의 매개변수로 1개만 넘길 수 있는 바, 객체/vo 클래스, 2개?인 경우 hashMap 등 개발자의 판단)에 담아서 db로 전달하기 -> Member vo 클래스 만듦 -> 관련 vo로 가공
		Member m = new Member(userId, userPwd, userName, email, birthday, gender, phone, address);
		
		int result = new MemberServiceImpl().insertMember(m); // insert의 처리 결과 = 0(실패 시) 또는 1(성공 시)
		
		if (result > 0) { // 회원 가입 성공한 경우 -> main page 응답
			// 아래 내용을 requestDispatcher로 응답해도 상관은 없음
			response.sendRedirect(request.getContextPath());
		} else { // 회원 가입 실패한 경우 -> errorMsg 담아서 errorPage 응답
			request.setAttribute("errorMsg", "회원 가입에 실패했습니다");
			
			// 위와 같은 속성 담은 request 객체 forwarding
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
