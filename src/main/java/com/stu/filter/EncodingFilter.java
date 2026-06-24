package com.stu.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局字符编码过滤器
 * 统一设置请求和响应的字符编码为UTF-8，避免中文乱码
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 设置请求编码
        req.setCharacterEncoding(ENCODING);
        // 设置响应编码
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType("text/html;charset=" + ENCODING);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
