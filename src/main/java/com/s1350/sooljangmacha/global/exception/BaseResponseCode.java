package com.s1350.sooljangmacha.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {

    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다."),

    REQUEST_VALIDATION("E0001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    REQUEST_NOT_READABLE("E0002", HttpStatus.BAD_REQUEST, "잘못된 데이터 포맷입니다."),
    DATABASE_ERROR("E0003", HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 관련 에러입니다."),
    INTERNAL_SERVER_ERROR("E0004", HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

    public final String code;
    public final HttpStatus status;
    public final String message;
}
