package com.s1350.sooljangmacha.store.repository;

import com.s1350.sooljangmacha.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Boolean existsByXAndYAndIsEnable(String x, String y, Boolean isEnable);
}
