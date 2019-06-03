package com.coco.Filter;

/**
 * @Classname AjaxFilter
 * @Description TODO
 * @Date 19-6-3 下午3:39
 * @Created by ltt
 */

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AjaxFilter extends OncePerRequestFilter {

    /**
    * @Description 此文件令我疑惑，暂时让它存在这儿
    * @return void
    *
    **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //此行代码确保请求可以继续执行至Controller
        filterChain.doFilter(request, response);
    }
}