package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.service.MemberServiceImpl;
import com.kh.mybatis.member.model.vo.Member;

// 2022.2.10(목) 10h5
/**
 * Servlet implementation class MemberLoginController
 */
@WebServlet("/login.me")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		String userId = request.getParameter("userId"); // getParameter() 반환형 = String
//		String userPwd = request.getParameter("userPwd");
		
		Member m = new Member();
		// 생각해보니 request 객체에 담긴 userId, userPwd 값들 다시 쓸 일 없을 것 같으므로, 굳이 String 변수에 담지 않기로 함
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		
		// SELECT문으로 해당 userId, userPwd로 조회하고, 조회된 사용자의 정보들을 받아오고자 함
		Member loginUser = new MemberServiceImpl().loginMember(m); // id 및 비밀번호 일치하는 활성 회원 정보 1행 ou null
		
		if (loginUser == null) { // 로그인 실패
			request.setAttribute("errorMsg", "로그인에 실패했습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		} else { // 로그인 성공
			// 로그인한 회원의 정보는 로그아웃 ou 브라우저 닫기 전까지 web app 여기저기서  사용할 것이기 때문에, loginUser를 담을 scope가 최소한 session이어야 하는 바, session 객체에 담아두고자 함
			// application 객체에 담으려면, 그 아래 범위 객체들을 거쳐서 application 객체를 생성해야 하는 바(+11h15 강사님 추가 설명 제대로 못 들음), 굳이 일을 하나 더 할 필요 없음
			request.getSession().setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath());
		}
		
	} // 2022.2.10(목) 11h20 초안 작성 마무리 -> 11h25 테스트 완료

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
