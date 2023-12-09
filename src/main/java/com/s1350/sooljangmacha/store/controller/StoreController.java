package com.s1350.sooljangmacha.store.controller;

import com.s1350.sooljangmacha.global.dto.BaseResponse;
import com.s1350.sooljangmacha.global.resolver.UserAccount;
import com.s1350.sooljangmacha.store.dto.request.PostStoreReq;
import com.s1350.sooljangmacha.store.dto.request.PostStoreReviewReq;
import com.s1350.sooljangmacha.store.dto.response.GetStoreReviewRes;
import com.s1350.sooljangmacha.store.service.StoreService;
import com.s1350.sooljangmacha.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @Operation(summary = "[윤희슬] 포장마차 좋아요", description = "포장마차를 좋아요(즐겨찾기)한다.")
    @PostMapping ("/{storeId}/likes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)포장마차 등록 성공"),
            @ApiResponse(responseCode = "400", description = "(ST0002)이미 존재하는 포차입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    public BaseResponse postStoreLike(@Parameter(hidden = true) @UserAccount User user,
                                  @PathVariable(name = "storeId") Long storeId){
        storeService.postStoreLike(user, storeId);
        return BaseResponse.OK();
    }


    @Operation(summary = "[장채은] 포장마차 등록", description = "포장마차를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)포장마차 등록 성공"),
            @ApiResponse(responseCode = "400", description = "(U0002)휴대폰 형식을 확인해주세요. <br> (E0001)잘못된 요청입니다. <br> (ST0001)이미 존재하는 포차입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping("")
    public BaseResponse postStore(@Parameter(hidden = true) @UserAccount User user,
                                  @RequestBody @Valid PostStoreReq req){
        storeService.postStore(req);
        return BaseResponse.OK();
    }

    @Operation(summary = "[장채은] 포장마차 후기 조회", description = "포장마차 후기 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)포장마차 후기 조회 성공"),
    })
    @GetMapping("/{storeId}/reviews")
    public BaseResponse<List<GetStoreReviewRes>> getStoreReview(@Parameter(hidden = true) @UserAccount User user,
                                                                @PathVariable(name = "storeId") Long storeId){
        return BaseResponse.OK(storeService.getStoreReview(storeId));
    }

    @Operation(summary = "[장채은] 포장마차 후기 등록", description = "포장마차 후기를 등록한다.")
    @PostMapping("/{storeId}/reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)포장마차 후기 등록 성공"),
            @ApiResponse(responseCode = "400", description = "(E0001)잘못된 요청입니다. <br> (ST0002)존재하지 않는 포차입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    public BaseResponse postStoreReview(@Parameter(hidden = true) @UserAccount User user,
                                        @PathVariable(name = "storeId") Long storeId,
                                        @RequestBody @Valid PostStoreReviewReq req){
        storeService.postStoreReview(user, storeId, req);
        return BaseResponse.OK();
    }
}
