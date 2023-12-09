package com.s1350.sooljangmacha.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostStoreReq {
    @Schema(type = "String", description = "상점 이름", example = "술장마차1호점", required = true)
    @NotBlank
    private String name;
    @Schema(type = "String", description = "상점 주소", example = "서울특별시 서대문구 거북골로 34", required = true)
    @NotBlank
    private String address;
    @Schema(type = "String", description = "상점 좌표 경도", example = "50.123456", required = true)
    @NotBlank
    private String x;
    @Schema(type = "String", description = "상점 좌표 위도", example = "50.123456", required = true)
    @NotBlank
    private String y;
    @Schema(type = "String", description = "상점 소개", example = "술장마차 1호점 매주 월요일 휴무")
    private String content;
    @Schema(type = "String", description = "상점 전화번호", example = "010-9999-9999")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String phone;
}
