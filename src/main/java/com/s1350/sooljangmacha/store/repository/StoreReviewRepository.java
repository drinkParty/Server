package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.StoreReview;
import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
    void deleteByUser(User user);
}
