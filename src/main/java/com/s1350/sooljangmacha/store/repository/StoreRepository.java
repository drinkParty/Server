package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Boolean existsByXAndYAndIsEnable(String x, String y, Boolean isEnable);
    Optional<Store> findByIdAndIsEnable(Long storeId, Boolean isEnable);
    List<Store> findAllByIsEnable(Boolean isEnable);
}
