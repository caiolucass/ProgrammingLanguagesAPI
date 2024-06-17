package com.example.hotmart.adesao.repository;

import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {
    @Query("SELECT l.title, COUNT(u) FROM User u JOIN u.languages l GROUP BY l.title")
    List<User[]> countUsersByProgrammingLanguages(@Param("language") String language);

    @Query("SELECT DISTINCT u FROM User u JOIN u.languages l WHERE l.title IN :language")
    List<User> findUserByLanguages(@Param("language") String language);
}
