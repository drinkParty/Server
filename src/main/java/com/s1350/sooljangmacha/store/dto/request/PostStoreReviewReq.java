package com.s1350.sooljangmacha.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostStoreReviewReq {
    @Schema(type = "String", description = "후기", example = "너무너무 맛있습니다!", required = true)
    @NotBlank(message = "후기 내용을 입력해주세요.")
    private String content;
}
