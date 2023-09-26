package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.vo.Member;

// 2022.2.9(수) 15h5
// interface는 상수 필드(public static final)와 추상메소드(public abstract)만 가질 수 있음 -> 별도의/다른 클래스에서 이 추상메소드(들의 body)를 구현

// (semi-)project = 기획 -> 화면 설계 -> erd/db 설계 -> 클래스 설계(어려움/난이도 높음) -> 기능 구현
// project에 투입되면, project manager = 개발자 ou 기획자 출신 = project의 대장; project 수행을 위해 역할 분담 -> 개발팀이 해야할 일을 개발팀장에게 줌
// 개발팀장 = 클래스 설계 -> interface(메소드 signature 포함) 줌 -> 개발팀원들은 그걸 바탕으로 기능 구현  -> 실무에서 내가 interface를 만들 일은 거의 없을 것임
public interface MemberService {
	// 회원 관련 기능/메소드 = 회원 가입, 로그인, 회원 정보 수정, 회원 탈퇴..
	
	// 회원 가입
	int insertMember(Member m);
	
	// 로그인 <- 사용자가 입력한 id 및 비밀번호를 배열이나 hashMap이나 Member 객체에 담아서 받음
	Member loginMember(Member m);
	
	// 회원 정보 수정
	int updateMember(Member m);
	
	// 회원 탈퇴
	int deleteMember(Member m);

}
