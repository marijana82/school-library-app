package com.marijana.library1223.repositories;

import com.marijana.library1223.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, String> {

}
