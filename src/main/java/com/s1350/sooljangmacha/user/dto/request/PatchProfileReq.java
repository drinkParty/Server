package com.s1350.sooljangmacha.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PatchProfileReq {

    @Schema(type = "String", description = "유저 닉네임", example = "띵동", required = true)
    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickname;

    @Schema(type = "String", description = "유저 이미지 key (동일하면 url로 전송)", example = "ex.png")
    private String imgKey;

    @Schema(type = "String", description = "유저 주소", example = "서울시 서대문구")
    private String address;

}
