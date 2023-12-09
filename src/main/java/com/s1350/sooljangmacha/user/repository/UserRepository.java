package com.s1350.sooljangmacha.user.repository;

import com.s1350.sooljangmacha.user.entity.Provider;
import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndIsEnable(Long id, boolean isEnable);

    Optional<User> findByEmailAndProviderAndIsEnable(String email, Provider provider, boolean isEnable);

    boolean existsByEmailAndProviderAndIsEnable(String email, Provider provider, boolean isEnable);

}
