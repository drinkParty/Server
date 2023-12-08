package com.s1350.sooljangmacha.user.controller;

import com.s1350.sooljangmacha.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 로그인

    // 로그아웃

    // 회원탈퇴

    // 프로필 불러오기

    // 프로필 편집

    // 포장마차 좋아요 목록 조회

}
