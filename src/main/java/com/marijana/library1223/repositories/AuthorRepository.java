package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
