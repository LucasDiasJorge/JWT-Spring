package com.api.project.configs.security;

import com.api.project.data.UserDetailsData;
import com.api.project.model.UserModel;
import com.api.project.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public static final int TOKEN_EXPIRATION = 600_000;

    public static final String TOKEN_PASS = "0f96631d-b479-416d-a8b6-d2815ad77f00";

    public static final int maxAttempts = 3;

    final UserRepository userRepository;

    public JWTAuthFilter(AuthenticationManager authenticationManager, UserRepository userRepository) throws FileNotFoundException {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserModel user = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            UserModel userVerify = userRepository.findByEmail(user.getEmail());
            if(userVerify.getAttempts() < maxAttempts) {
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPass(), user.getRoles()
                ));
            } else {
                throw new RuntimeException("Attempts exceeds");
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to auth", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsData userDetailsData = (UserDetailsData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(userDetailsData.getUsername())
                .withClaim("roles",userRepository.findByEmail(userDetailsData.getUsername()).getRoles().get(0).getRoleName().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                //.withExpiresAt(new Date(System.currentTimeMillis() + 5000000000000L)) Token vitalicio
                .sign(Algorithm.HMAC512(TOKEN_PASS));

        System.out.println(authResult.getAuthorities().toString());

        response.getWriter().write("{ \n    \"Token\" : \"" + token + "\" \n}");
        response.getWriter().flush();
    }
}
