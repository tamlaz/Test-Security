package com.test.repository;

import com.test.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    @Query("select c from CustomUser c where c.email = :email")
    public Optional<CustomUser> findByEmail(@Param("email") String email);
}
