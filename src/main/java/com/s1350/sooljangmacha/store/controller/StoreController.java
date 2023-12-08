package com.s1350.sooljangmacha.store.controller;

import com.s1350.sooljangmacha.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    // 위치별 포장마차 전체 조회

    // 포장마차 상세 조회

    // 포장마차 좋아요

    // 포장마차 등록

    // 포장마차 후기 조회

    // 포장마차 후기 등록
}
