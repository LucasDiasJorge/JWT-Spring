package com.project.auth.repository;

import com.project.auth.model.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository extends JpaRepository<AbstractModel, Long> {

}
