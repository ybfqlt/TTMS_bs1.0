package com.coco.Interceptor;

/**
 * @Classname Crossdomain
 * @Description 跨域请求的拦截器
 * @Date 19-5-27 下午5:02
 * @Created by ltt
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class Crossdomain implements HandlerInterceptor {

    private List<String> excludedUrls;

    public void setExcludedUrls(List<String> excludedUrls){
        this.excludedUrls=excludedUrls;
    }
    public List<String> getExcludedUrls(){
        return excludedUrls;
    }


    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        if(request.getHeader("Origin")!=null) {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json,charset=utf-8");

            /*response.setHeader("Access-Control-Allow-Origin", "*");*/
            response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "20000");
            /*response.setHeader("Access-Control-Allow-Credentials","true");//***重点是否支持cookie跨域*/
            response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        }
        return true;
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{

    }
}
