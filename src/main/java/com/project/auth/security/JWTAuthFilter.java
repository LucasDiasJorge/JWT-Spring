package com.project.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.auth.data.UserDetailsData;
import com.project.auth.model.UserModel;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public static final int TOKEN_EXPIRATION = 600_000;

    public static final String TOKEN_PASS = "0f96631d-b479-416d-a8b6-d2815ad77f00";

    public static final int maxAttempts = 3;

    public JWTAuthFilter(AuthenticationManager authenticationManager) throws FileNotFoundException {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserModel user = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            if(user.getAttempts() < maxAttempts) {
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPass(), new ArrayList<>()
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
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASS));

        Map<String, Object> ret = new HashMap<>();

        response.getWriter().write("{ \"Token\" : \"" + token + "\" }");
        response.getWriter().flush();
    }
}
