package com.api.project.configs.security;

import com.api.project.model.UserModel;
import com.api.project.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
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
import java.util.Map;

public class JWTFilterValidator extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTE  = "Authorization";

    public static final String ATRIBUTE_PREFIX  = "Bearer ";

    final UserRepository userRepository;

    public JWTFilterValidator(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
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

        Map<String, Claim> roles = JWT.require(Algorithm.HMAC512(JWTAuthFilter.TOKEN_PASS))
                .build()
                .verify(token)
                .getClaims();

        UserModel userModel = userRepository.findByEmail(user);

        System.out.println(roles);
        System.out.println(userModel.getAuthorities().stream());

        return new UsernamePasswordAuthenticationToken(user,null,userModel.getAuthorities()); // ArrayList = permissionList

    }

}
