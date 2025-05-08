package com.quickbite.backend.repository;

import com.quickbite.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrMobileNo(String email, String mobile);
}
