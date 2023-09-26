package com.kh.mybatis.board.model.vo;

import java.sql.Date;

// 2022.2.10(목) 12h5
public class Board {

	private int boardNo; // BOARD_NO	NUMBER	No		1
	private String boardTitle; // BOARD_TITLE	VARCHAR2(100 BYTE)	No		2
	private String boardContent; // BOARD_CONTENT	VARCHAR2(4000 BYTE)	No		3
	private String boardWriter; // BOARD_WRITER	NUMBER	Yes		4 -> BOARD 테이블 컬럼 값은 userNo(int 자료형)이 담겨있으나, db 조회 시 userId(String)도 담을 수 있도록, 일부러 String으로 선언함
	private int count; // COUNT	NUMBER	Yes	0	5
	private Date createDate; // CREATE_DATE	DATE	Yes	SYSDATE	6
	private String status; // STATUS	VARCHAR2(1 BYTE)	Yes	'Y' 	7
	
	public Board() {
		super();
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, int count, Date createDate,
			String status) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", count=" + count + ", createDate=" + createDate + ", status="
				+ status + "]";
	}
	
}
