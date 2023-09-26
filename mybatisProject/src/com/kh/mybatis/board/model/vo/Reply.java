package com.kh.mybatis.board.model.vo;

import java.sql.Date;

// 2022.2.11(금) 10h5
public class Reply {
	
	private int replyNo; // REPLY_NO	NUMBER	No		1
	private String replyContent; // REPLY_CONTENT	VARCHAR2(400 BYTE)	Yes		2
	private int refBoardNo; // REF_BNO	NUMBER	Yes		3
	private String replyWriter; // REPLY_WRITER	NUMBER	Yes		4
	private Date createDate; // CREATE_DATE	DATE	Yes	SYSDATE	5
	private String status; // STATUS	VARCHAR2(1 BYTE)	Yes	'Y' 	6
	
	public Reply() {
		super();
	}

	public Reply(int replyNo, String replyContent, int refBoardNo, String replyWriter, Date createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBoardNo = refBoardNo;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
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
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBoardNo=" + refBoardNo
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}

}
