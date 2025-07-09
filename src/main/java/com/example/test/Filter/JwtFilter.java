package com.example.test.Filter;

import com.example.test.Service.JwtService;
import com.example.test.Service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserDetailService userDetailService;

    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse respone , FilterChain filter) throws ServletException , IOException{
        String Auth = request.getHeader("Authorization");
        String Token = null;
        String username=null;

        if(Auth!=null && Auth.startsWith("Bearer")){
            Token = Auth.substring(7);
            username=jwtService.extractUsername(Token);
        }

        if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if(jwtService.isTokenValid(Token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filter.doFilter(request,respone);
    }



}
