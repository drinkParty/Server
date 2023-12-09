package com.s1350.sooljangmacha.store.controller;

import com.s1350.sooljangmacha.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "stores", description = "포장마차 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

//    @Operation(summary = "위치별 포장마차 전체 조회", description = "")
//    @GetMapping("")

//    @Operation(summary = "포장마차 상세 조회", description = "")
//    @GetMapping("/{storeId}")

//    @Operation(summary = "포장마차 좋아요", description = "")
//    @PostMapping ("/{storeId}/likes")

//    @Operation(summary = "포장마차 등록", description = "")
//    @PostMapping("")

//    @Operation(summary = "포장마차 후기 조회", description = "")
//    @GetMapping("/{storeId}/reviews")

//    @Operation(summary = "포장마차 후기 등록", description = "")
//    @PostMapping("/{storeId}/reviews")
}
