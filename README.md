# coco影院票务管理系统  
&#160;&#160;采用前后端分离方式进行开发   
前端代码：https://github.com/ybfqlt/HTML_TTMS     
演示访问:http://120.78.64.17:8080/HTML_TTMS/html/Login.html     
经理只能通过管理员进行添加，管理员账号唯一   
示例登录经理账号：用户名: zxcvbnm 密码:zxcvbnml  
---  
## 概述
&#160;&#160;coco影院票务管理系统是一个面向用户(登录和未登录)，经理，管理员的平台，其中:   
- 用户可以查看电影及其详细信息，选择场次，然后选座买票(可以一次选多个座位买多张票),当然买票仅限登录用户  
- 经理可以对影厅，演出计划，剧目进行管理。  
- 管理员则可以管理用户。 
## 职责
&#160;&#160;此项目为团队合作项目，我作为组长,负责所有的后端开发， 以及项目部署,其他组员进行前端开发，后期，我对前端有过3%的修改和添加。

## 系统框架  
![coco影院票务管理系统](https://github.com/ybfqlt/TTMS_bs1.0/blob/master/docs/%E5%9B%BE%E7%89%871.png)

## 遇到的问题
1. 前后端分离遇到跨域问题，前端的请求发送不到后端。　
解决：加一个过滤器,设置一些头信息,如下:
```java
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
              filterChain.doFilter(servletRequest, servletResponse);
          }
```
  
2. 因为前后端分离，前端携带的session每刷新一次页面就会变一次，导致后断无法在session中保存登录状态。  
解决：前端发送ajax请求时添加如下代码?
```js
xhrFields:{
    withCredentials: true
}
``` 
3. 数据库的存储问题，因为数据库默认的为CST时区，导致每次存入的时间会改变 （滞后11个小时）    
解决：将数据库默认的时区改成东八区。        
4. 后端拦截器验证登录的时候，重定向到登录页时候，无效。   
原因: AJAX发送的请求只接收返回值，对于重定向请求不做处理。　　　  
解决：在后端拦截到未登录的时候，设置头，表示要重定向,以及要重定向到的url,前端添加一个公共js,js中对响应的头信息取值判断，如果有重定向标志，由前端进行跳转。


## 收获
&#160;&#160;这是学习完spring之后，自己完成的第一个项目，当时写完项目，对Spring MVC有了进一步的理解，它的流程，它的一些注解以及配置等等，以及使用Maven来管理jar包,还学会了将项目部署到服务器上，提高了团队合作能力，以及沟通能力。