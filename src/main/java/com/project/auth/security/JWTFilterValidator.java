package com.project.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.hibernate.Session;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTFilterValidator extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTE  = "Authorization";

    public static final String ATRIBUTE_PREFIX  = "Bearer ";

    public JWTFilterValidator(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String atribute = request.getHeader(HEADER_ATRIBUTE);

        System.out.println(request.getRequestURL());



        if(atribute == null){
            chain.doFilter(request, response);
            return;
        }

        if(!atribute.startsWith(ATRIBUTE_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        String token = atribute.replace(ATRIBUTE_PREFIX, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticatorToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticatorToken(String token){
        String user = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_PASS))
                .build()
                .verify(token)
                .getSubject();

        if(user == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>()); // ArrayList = permissionList

    }

}
