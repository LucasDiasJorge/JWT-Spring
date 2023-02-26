package com.api.project.repository;

import com.api.project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    public UserModel findByEmail(String email);

    public UserModel findByUsername(String username);

}
