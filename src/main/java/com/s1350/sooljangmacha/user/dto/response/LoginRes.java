package com.s1350.sooljangmacha.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {

    @Schema(type = "String", description = "액세스 토큰")
    private String accessToken;

    public static LoginRes toEntity(String accessToken) {
        return LoginRes.builder()
                .accessToken(accessToken)
                .build();
    }
}
