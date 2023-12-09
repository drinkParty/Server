package com.s1350.sooljangmacha.global.dto;

import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private final int status;
    private final String code;
    private final String message;
    private T data;

    public static BaseResponse OK() {
        BaseResponseCode baseResponseCode = BaseResponseCode.SUCCESS;
        return new BaseResponse<>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage());
    }

    public static <T> BaseResponse<T> OK(BaseResponseCode baseResponseCode, T data) {
        return new BaseResponse<T>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage(), data);
    }

    public static <T> BaseResponse<T> OK(@Nullable T data) {
        BaseResponseCode baseResponseCode = BaseResponseCode.SUCCESS;
        return new BaseResponse<T>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage(), data);
    }

    public static BaseResponse error(BaseResponseCode baseResponseCode, String message) {
        return new BaseResponse<>(baseResponseCode.getStatus().value(), baseResponseCode.getCode(), baseResponseCode.getMessage());
    }

}
