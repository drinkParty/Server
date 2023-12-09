package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.StoreLike;
import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {
    void deleteByUser(User user);
}
