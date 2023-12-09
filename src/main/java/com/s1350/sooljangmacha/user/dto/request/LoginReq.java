package com.s1350.sooljangmacha.user.dto.request;

import com.s1350.sooljangmacha.global.resolver.EnumValid;
import com.s1350.sooljangmacha.user.entity.Provider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginReq {

    @Schema(type = "String", description = "유저 이메일", example = "ex@naver.com", required = true)
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "올바른 이메일 형식으로 입력해 주세요.")
    private String email;

    @Schema(type = "String", description = "유저 로그인 종류", example = "KAKAO", allowableValues = {"KAKAO", "APPLE"}, required = true)
    @NotBlank(message = "provider를 입력해 주세요.")
    @EnumValid(enumClass = Provider.class, ignoreCase = true, message = "잘못된 provider 값 입니다.")
    private String provider;

}
