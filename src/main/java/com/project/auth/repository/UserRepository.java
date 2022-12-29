package com.project.auth.repository;

import com.project.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    public UserModel findByEmail(String email);

    public UserModel findByCard(String card);

}
