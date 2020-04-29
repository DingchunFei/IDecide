package com.uom.idecide.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT的拦截器,拦截器有两种实现方法
 * (1) 实现HandlerInterceptor接口
 * (2) 继承HandlerInterceptorAdapter类
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 无论如何都放行，具体能不能操作在具体的操作中判断，
     * 拦截器只是负责把有请求头中包含token的令牌进行一个解析
     * 至于这个请求的Authorization，还是交给业务层来判断！！！
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //把token从header中取出
        String header = request.getHeader("Authorization");
        System.out.println("interceptor has been executed!");
        if(header!=null && !"".equals(header)){
            //如果包含Token头信息，就对其进行解析
            if(header.startsWith("uom ")){
                String token = header.substring(4);
                //d对令牌进行验证
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    //尝试从令牌中获取当前用户的角色
                    String roles = (String) claims.get("roles");
                    if(roles != null && roles.equals("admin")){
                        //如果发现可以解析token，就把token放到request中，方便后面代码提取
                        request.setAttribute("claims_admin",token);
                    }else if(roles != null && roles.equals("user")){
                        //这个是普通用户的token标识
                        request.setAttribute("claims_user",token);
                    }else if(roles != null && roles.equals("researcher")){
                        //这个是普通用户的token标识
                        request.setAttribute("claims_researcher",token);
                    }
                }catch (Exception e){
                    //抛出异常说明 超时！ 因此还是显示权限不足
                    throw new RuntimeException("令牌不正确！");
                }
            }
        }
        //最后还是放行。因为这个拦截器的使命就是试图解析token，方便后面的业务直接获取token
        return true;
    }
}
