package org.scoula.ex03;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class Utf8Filter_ implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8"; // 기본 인코딩 설정
        }
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        /// ////////////////////////
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);
        /// ///////////////////////
    }

    @Override
    public void destroy() {
        // 필터 종료시 처리할 작업
    }
}