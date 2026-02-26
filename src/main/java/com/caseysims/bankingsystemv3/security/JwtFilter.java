package com.caseysims.bankingsystemv3.security;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter
{


    @Autowired
    JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {


        String path = request.getRequestURI();
        if(path.equals("/login") || path.equals("/createUser"))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        String token = authHeader.substring(7);
        if(jwtUtil.validateToken(token))
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        }


    }
}
