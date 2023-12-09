package com.s1350.sooljangmacha.global.exception;

import com.s1350.sooljangmacha.global.dto.BaseResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseResponse> handleBaseException(BaseException e) {
        BaseResponseCode baseResponseCode = e.getBaseResponseCode();
        return ResponseEntity.status(baseResponseCode.getStatus())
                .body(BaseResponse.error(baseResponseCode, baseResponseCode.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return BaseResponse.error(BaseResponseCode.REQUEST_VALIDATION, String.format("%s", fieldError.getDefaultMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return BaseResponse.error(BaseResponseCode.REQUEST_NOT_READABLE, String.format("%s", BaseResponseCode.REQUEST_NOT_READABLE.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected BaseResponse handleConstraintViolationException(ConstraintViolationException e) {
        return BaseResponse.error(BaseResponseCode.REQUEST_VALIDATION, String.format("%s. (%s)", BaseResponseCode.REQUEST_VALIDATION.getMessage(), e.getConstraintViolations()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return BaseResponse.error(BaseResponseCode.REQUEST_VALIDATION, String.format("%s. (%s)", BaseResponseCode.REQUEST_VALIDATION.getMessage(), e.getParameterName()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected BaseResponse handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return BaseResponse.error(BaseResponseCode.REQUEST_VALIDATION, String.format("%s. (%s)", BaseResponseCode.REQUEST_VALIDATION.getMessage(), e.getHeaderName()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public BaseResponse handleDataAccessException(DataAccessException e) {
        e.printStackTrace();
        return BaseResponse.error(BaseResponseCode.DATABASE_ERROR, String.format("%s", BaseResponseCode.DATABASE_ERROR.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleHttpMessageNotReadableException(Exception e) {
        e.printStackTrace();
        return BaseResponse.error(BaseResponseCode.INTERNAL_SERVER_ERROR, String.format("%s", BaseResponseCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

}
