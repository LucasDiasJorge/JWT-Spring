package com.api.project.controller;

import com.api.project.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(("/api/common"))
public class CommonController {

    @GetMapping("/ping")
    public Map<String,Object> ping(){

        Map<String,Object> ret = new HashMap<>();

        ret.put("Version", "1.0.0.0.0");

        return ret;

    }

}
