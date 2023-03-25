package com.api.project.controller;

import com.api.project.HttpUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(("/api/common"))
public class CommonController {

    private final JdbcTemplate jdbcTemplate;

    public CommonController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/ping")
    public Map<String,Object> ping(HttpServletRequest request){

        Map<String,Object> ret = new HashMap<>();

        String ip = HttpUtils.getRequestIP(request);

        System.out.println("From:" + ip);

        ret.put("Version", "1.0.0.0.0");

        return ret;

    }

}
