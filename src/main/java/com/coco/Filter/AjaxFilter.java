package com.coco.Filter;

/**
 * @Classname 处理跨域的过滤器
 * @Description TODO
 * @Date 19-6-3 下午3:39
 * @Created by ltt
 */


import org.apache.commons.lang.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String orign = ((HttpServletRequest)servletRequest).getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", StringUtils.isEmpty(orign)?"*":orign);
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

//        if (((HttpServletRequest) servletRequest).getMethod().equals(RequestMethod.OPTIONS.name())){
//            response.setStatus(HttpStatus.OK.value());
//            return;
//        }
        //此行代码确保请求可以继续执行至Controller
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}