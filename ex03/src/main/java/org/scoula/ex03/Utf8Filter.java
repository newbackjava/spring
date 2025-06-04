package org.scoula.ex03;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class Utf8Filter implements Filter {
    private String encoding; //null로 초기화!

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        //FilterConfig --> web.xml에 filter에 사용할 변수를
        //설정해두고 읽어올 수 있음.
        if (encoding == null) {
            encoding = "UTF-8";
        }
        System.out.println("필터객체 생성됨.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("필터객체 소멸됨.");
    }
}
