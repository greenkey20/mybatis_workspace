package com.kh.mybatis.member.model.vo;

import java.sql.Date;

// 2022.2.9(수) 14h45
public class Member {
	
	private int userNo; // USER_NO	NUMBER	No		1
	private String userId; // USER_ID	VARCHAR2(30 BYTE)	No		2
	private String userPwd; // USER_PWD	VARCHAR2(100 BYTE)	No		3
	private String userName; // USER_NAME	VARCHAR2(15 BYTE)	No		4
	private String email; // EMAIL	VARCHAR2(100 BYTE)	Yes		5
	private String birthday; // BIRTHDAY	VARCHAR2(6 BYTE)	Yes		6 -> 0으로 시작하는 경우 int로 받으면 잘 입력 안 될 수 있는 바, varchar2/String으로 정의함
	private String gender; // GENDER	VARCHAR2(1 BYTE)	Yes		7
	private String phone; // PHONE	VARCHAR2(13 BYTE)	Yes		8
	private String address; // ADDRESS	VARCHAR2(100 BYTE)	Yes		9
	private Date enrollDate; // ENROLL_DATE	DATE	Yes	SYSDATE	10
	private Date modifyDate; // MODIFY_DATE	DATE	Yes	SYSDATE	11
	private String status; // STATUS	VARCHAR2(1 BYTE)	Yes	'Y' 	12
	
	public Member() {
		super();
	}

	public Member(int userNo, String userId, String userPwd, String userName, String email, String birthday,
			String gender, String phone, String address, Date enrollDate, Date modifyDate, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	
	// 2022.2.9(수) 15h 사용자가 입력한 값만 매개변수로 받는 생성자 추가 -> 회원 가입 처리 시 사용
	public Member(String userId, String userPwd, String userName, String email, String birthday, String gender,
			String phone, String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", birthday=" + birthday + ", gender=" + gender + ", phone=" + phone
				+ ", address=" + address + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
				+ status + "]";
	}

}
