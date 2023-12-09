package com.s1350.sooljangmacha.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseCode {

    SUCCESS("S0001", HttpStatus.OK, "요청에 성공했습니다."),

    NULL_TOKEN("T0001", HttpStatus.UNAUTHORIZED, "토큰 값을 입력해주세요."),
    INVALID_TOKEN("T0002", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰 값입니다."),
    UNSUPPORTED_TOKEN("T0003", HttpStatus.UNAUTHORIZED, "잘못된 형식의 토큰 값입니다."),
    MALFORMED_TOKEN("T0004", HttpStatus.UNAUTHORIZED, "잘못된 구조의 토큰 값입니다."),
    EXPIRED_TOKEN("T0005", HttpStatus.FORBIDDEN, "만료된 토큰 값입니다."),
    NOT_ACCESS_HEADER("T0006", HttpStatus.INTERNAL_SERVER_ERROR, "헤더에 접근할 수 없습니다."),

    REQUEST_VALIDATION("E0001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    REQUEST_NOT_READABLE("E0002", HttpStatus.BAD_REQUEST, "잘못된 데이터 포맷입니다."),
    DATABASE_ERROR("E0003", HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 관련 에러입니다."),
    INTERNAL_SERVER_ERROR("E0004", HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),

    EXISTS_STORE("ST0001", HttpStatus.BAD_REQUEST, "이미 존재하는 포장마차입니다."),

    USER_NOT_FOUND("U0001", HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    USER_ALREADY_EXIST("U0002", HttpStatus.BAD_REQUEST, "이미 가입된 유저입니다.");

    public final String code;
    public final HttpStatus status;
    public final String message;
}
