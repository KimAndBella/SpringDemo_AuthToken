package com.moku.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pan.sun on 2018/4/9.
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println("头信息 = "+authHeader);
        if("OPTIONS".equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            if(authHeader == null || !authHeader.startsWith("Bearer")){
                throw new ServletException("不合法的头信息");
            }
            String token = authHeader.substring(7);
            System.out.println("获取的token = "+token);
            try {
                Claims claims = Jwts.parser().setSigningKey("kimTestPasswordKey").parseClaimsJws(token).getBody();
                httpServletRequest.setAttribute("claims", claims);
            } catch (Exception e) {
                throw new ServletException("Invalid Token");
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
