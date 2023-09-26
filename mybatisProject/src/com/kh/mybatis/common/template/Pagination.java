package com.kh.mybatis.common.template;

import com.kh.mybatis.common.model.vo.PageInfo;

// 2022.2.10(목) 12h45
public class Pagination {
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; 
		int endPage = startPage + pageLimit - 1;
		// startPage가 11이라서 endPage가 20이 되어야 하는데, maxPage가 11이라면/총 11페이지까지 밖에 없다면, endPage를 maxPage로 변경
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 2022.2.10(목) 14h15
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		return pi;
	} // getPageInfo() 종료

}
