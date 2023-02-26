package com.api.project.controller;

import com.api.project.model.UserModel;
import com.api.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/api/user"))
public class UserController {

    private UserDetails userDetails;

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserController(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find")
    public ResponseEntity<List<UserModel>> findAllUsers(){

        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<UserModel> save(@RequestBody UserModel userModel, HttpServletRequest request, Authentication authResult) throws Exception {

        UserModel user = userRepository.findByEmail(userModel.getEmail());
        Optional<UserModel> opt = Optional.ofNullable(user);
        if(!opt.isEmpty()) {
            throw new Exception("user already exist");
        }

        userModel.setPass(encoder.encode(userModel.getPass()));
        return ResponseEntity.ok(userRepository.save(userModel));
    }

    @GetMapping("/pass-validator")
    public ResponseEntity<Boolean> passValidator(@RequestParam String email,
                                                 @RequestParam String pass){

        UserModel user = userRepository.findByEmail(email);
        Optional<UserModel> opt = Optional.ofNullable(user);
        if(!opt.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        user = opt.get();

        boolean valid = encoder.matches(pass, user.getPass());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        user.setLastLogin(new Date());

        return ResponseEntity.status(status).body(valid);

    }

}
