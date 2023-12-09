package com.s1350.sooljangmacha.user.controller;

import com.s1350.sooljangmacha.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

//    @Operation(summary = "로그인", description = "")
//    @PostMapping("/login")

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
