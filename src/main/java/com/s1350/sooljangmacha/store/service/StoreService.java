package com.s1350.sooljangmacha.store.service;

import com.s1350.sooljangmacha.store.repository.StoreImgRepository;
import com.s1350.sooljangmacha.store.repository.StoreLikeRepository;
import com.s1350.sooljangmacha.store.repository.StoreRepository;
import com.s1350.sooljangmacha.store.repository.StoreReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreLikeRepository storeLikeRepository;
    private final StoreImgRepository storeImgRepository;
    private final StoreReviewRepository storeReviewRepository;

    // 위치별 포장마차 전체 조회

    // 포장마차 상세 조회

    // 포장마차 좋아요

    // 포장마차 등록

    // 포장마차 후기 조회

    // 포장마차 후기 등록

}
