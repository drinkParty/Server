package com.s1350.sooljangmacha.user.controller;

import com.s1350.sooljangmacha.global.dto.BaseResponse;
import com.s1350.sooljangmacha.global.resolver.UserAccount;
import com.s1350.sooljangmacha.store.dto.response.GetStoreListRes;
import com.s1350.sooljangmacha.user.dto.request.LoginReq;
import com.s1350.sooljangmacha.user.dto.request.PatchProfileReq;
import com.s1350.sooljangmacha.user.dto.request.SignupReq;
import com.s1350.sooljangmacha.user.dto.response.GetProfileRes;
import com.s1350.sooljangmacha.user.dto.response.LoginRes;
import com.s1350.sooljangmacha.user.entity.User;
import com.s1350.sooljangmacha.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "users", description = "유저 API")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
            @ApiResponse(responseCode = "400", description = "(E0001)잘못된 요청입니다. <br> (E0001)닉네임을 입력해 주세요. <br> (E0001)이메일을 입력해 주세요. <br> (E0001)provider를 입력해 주세요. <br> (E0001)올바른 이메일 형식으로 입력해 주세요. <br> (E0001)잘못된 provider 값 입니다. <br> (U0002)이미 가입된 유저입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    })
    @PostMapping("/signup")
    public BaseResponse<LoginRes> signup(@RequestBody @Valid SignupReq signupReq) {
        return BaseResponse.OK(userService.signup(signupReq));
    }

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

    @Operation(summary = "[김초원] 프로필 불러오기", description = "마이페이지의 프로필을 불러온다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping("")
    public BaseResponse<GetProfileRes> getProfile(@Parameter(hidden = true) @UserAccount User user) {
        return BaseResponse.OK(userService.getProfile(user));
    }

    @Operation(summary = "[김초원] 프로필 편집", description = "마이페이지의 프로필을 편집한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "(U0001)존재하지 않는 유저입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @PatchMapping("")
    public BaseResponse patchProfile(@Parameter(hidden = true) @UserAccount User user,
                                     @RequestBody @Valid PatchProfileReq patchProfileReq) {
        userService.patchProfile(user, patchProfileReq);
        return BaseResponse.OK();
    }

    @Operation(summary = "[김초원] 포장마차 좋아요 목록 조회", description = "마이페이지의 좋아요 한 포차 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "(E0001)잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class))),
    })
    @GetMapping("/likes")
    public BaseResponse<List<GetStoreListRes>> getStoreOfLike(@Parameter(hidden = true) @UserAccount User user,
                                                              @Parameter(description = "(String) 카테고리(좋아요순/후기순/최신순)", example = "후기순", required = true) @RequestParam(name = "category") String category) {
        return BaseResponse.OK(userService.getStoreOfLike(user, category));
    }

}
