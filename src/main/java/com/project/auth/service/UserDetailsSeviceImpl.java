package com.project.auth.service;

import com.project.auth.data.UserDetailsData;
import com.project.auth.model.UserModel;
import com.project.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDetailsSeviceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsSeviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserModel user = userRepository.findByEmail(email);
        Optional<UserModel> opt = Optional.ofNullable(user);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User [" + email + "] not found");
        }
        return new UserDetailsData(opt);
    }
}
