package com.s1350.sooljangmacha.user.controller;

import com.s1350.sooljangmacha.global.dto.BaseResponse;
import com.s1350.sooljangmacha.user.dto.request.LoginReq;
import com.s1350.sooljangmacha.user.dto.response.LoginRes;
import com.s1350.sooljangmacha.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "users", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "로그인", description = "로그인을 한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "(E0001)잘못된 요청입니다. <br> (E0001)이메일을 입력해 주세요. <br> (E0001)provider를 입력해 주세요. <br> (E0001)올바른 이메일 형식으로 입력해 주세요. <br> (E0001)잘못된 provider 값 입니다. <br>", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PostMapping("/login")
    public BaseResponse<LoginRes> login(@RequestBody @Valid LoginReq loginReq) {
        return BaseResponse.OK(userService.login(loginReq));
    }


//    @Operation(summary = "로그아웃", description = "")
//    @PostMapping("/logout")

//    @Operation(summary = "회원탈퇴", description = "")
//    @PostMapping("/signout")

//    @Operation(summary = "프로필 불러오기", description = "")
//    @GetMapping("")

//    @Operation(summary = "프로필 편집", description = "")
//    @PatchMapping("")

//    @Operation(summary = "포장마차 좋아요 목록 조회", description = "")
//    @GetMapping("/likes")

}
