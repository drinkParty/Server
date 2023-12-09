package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.Store;
import com.s1350.sooljangmacha.store.entity.StoreLike;
import com.s1350.sooljangmacha.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {
    Optional<StoreLike> findByUserAndStore(User user, Store store);

    @Query("SELECT COUNT(s.id) FROM StoreLike s WHERE s.isEnable = 1 and s.store = :store")
    Long getLikeCountByIsEnable(@Param("store") Store store);
}
