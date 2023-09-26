package com.kh.mybatis.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 2022.2.11(금) 17h5
// servlet이 공통적으로 하는 일 = encoding -> EncodingFilter 생성
/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*") // 모든 servlet이 실행되기 전에 이 filter를 거쳐가도록 함 vs 이 필터 처음 생성했을 때는 url mapping 값으로 "/EncodingFilter"라고 기재되어 있었음
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");

		// pass the request along the filter chain
		chain.doFilter(request, response); // servlet을 호출하는 구문; 단, 현재 필터가 최종 필터가 아닌 경우, 또 다른 필터를 호출
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
