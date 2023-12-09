package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.Store;
import com.s1350.sooljangmacha.store.entity.StoreLike;
import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {
    Optional<StoreLike> findByUserAndStore(User user, Store store);
}
