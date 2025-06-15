package com.example.auth.repository;

import com.example.auth.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
