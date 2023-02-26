package com.api.project.repository;

import com.api.project.model.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository extends JpaRepository<AbstractModel, Long> {

}
