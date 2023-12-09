package com.s1350.sooljangmacha.user.dto.response;

import com.s1350.sooljangmacha.global.utils.AwsS3Util;
import com.s1350.sooljangmacha.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProfileRes {

    @Schema(type = "String", description = "닉네임")
    private String nickname;

    @Schema(type = "String", description = "프로필 이미지")
    private String imgUrl;

    @Schema(type = "String", description = "주소")
    private String address;

    public static GetProfileRes toDto(User user) {
        return GetProfileRes.builder()
                .nickname(user.getNickname())
                .imgUrl(AwsS3Util.toUrl(user.getImgKey()))
                .address(user.getAddress())
                .build();
    }
}
