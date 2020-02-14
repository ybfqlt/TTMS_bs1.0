package com.coco.Interceptor;

/**
 * @Classname Crossdomain
 * @Description 登录的拦截器的拦截器
 * @Date 19-5-27 下午5:02
 * @Created by xns
 */

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    public static Logger log = Logger.getLogger(LoginInterceptor.class.getName());

    private List<String> excludeUrls;

    public void setExcludedUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public List<String> getExcludedUrls() {
        return excludeUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("login===============   " + request.getSession().getAttribute("login"));

        String requestUri = request.getRequestURI();
        if(requestUri.startsWith(request.getContextPath())) {
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }
        //放行exceptUrls中配置的url
        for (String url : excludeUrls) {
            log.debug(requestUri.startsWith(url));
            if (url.endsWith("/**")) {
                if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
                    return true;
                }
            } else if (requestUri.startsWith(url)) {
                return true;
            }
        }

        if (request.getSession().getAttribute("login") == null || !((boolean) (request.getSession().getAttribute("login")))) {
//            System.out.println(request.getContextPath());
            response.sendRedirect("http://120.78.64.17:8080/HTML_TTMS/html/Login.html");
            return false;
        }
        return true;
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }

}
