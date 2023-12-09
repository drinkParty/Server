package com.s1350.sooljangmacha.user.repository;

import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
