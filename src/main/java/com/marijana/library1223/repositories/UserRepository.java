package com.marijana.library1223.repositories;

import com.marijana.library1223.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, String> {

}
