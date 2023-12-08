package com.s1350.sooljangmacha.global.resolver;

import com.s1350.sooljangmacha.global.exception.BaseException;
import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import com.s1350.sooljangmacha.global.utils.JwtUtil;
import com.s1350.sooljangmacha.user.entity.User;
import com.s1350.sooljangmacha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import static com.s1350.sooljangmacha.global.Constants.AUTHORIZATION_HEADER;

@RequiredArgsConstructor
@Component
public class UserAccountResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserAccount.class) && User.class.equals(parameter.getParameterType());
    }

    @Override
    public User resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (!StringUtils.hasText(header)) throw new BaseException(BaseResponseCode.NULL_TOKEN);
        final String token = JwtUtil.replaceBearer(header);

        if (!jwtUtil.validateToken(token)) throw new BaseException(BaseResponseCode.INVALID_TOKEN);
        return userRepository.findByIdAndIsEnable(jwtUtil.getJwtContents(token), true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    }
}

