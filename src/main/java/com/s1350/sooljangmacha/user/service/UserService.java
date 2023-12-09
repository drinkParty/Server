package com.s1350.sooljangmacha.user.service;


import com.s1350.sooljangmacha.global.exception.BaseException;
import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import com.s1350.sooljangmacha.global.utils.JwtUtil;
import com.s1350.sooljangmacha.store.dto.response.GetStoreListRes;
import com.s1350.sooljangmacha.store.repository.StoreLikeRepository;
import com.s1350.sooljangmacha.store.service.StoreService;
import com.s1350.sooljangmacha.user.dto.request.LoginReq;
import com.s1350.sooljangmacha.user.dto.request.PatchProfileReq;
import com.s1350.sooljangmacha.user.dto.request.SignupReq;
import com.s1350.sooljangmacha.user.dto.response.GetProfileRes;
import com.s1350.sooljangmacha.user.dto.response.LoginRes;
import com.s1350.sooljangmacha.user.entity.Provider;
import com.s1350.sooljangmacha.user.entity.User;
import com.s1350.sooljangmacha.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final StoreLikeRepository storeLikeRepository;
    private final StoreService storeService;

    // 로그인
    public LoginRes login(LoginReq request) {
        User user = userRepository.findByEmailAndProviderAndIsEnable(request.getEmail(), Provider.valueOf(request.getProvider()), true)
                .orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        return LoginRes.toEntity(jwtUtil.issuedAccessToken(user.getId()));
    }

    // 회원가입
    @Transactional
    public LoginRes signup(SignupReq request) {
        if (userRepository.existsByEmailAndProviderAndIsEnable(request.getEmail(), Provider.valueOf(request.getProvider()), true))
            throw new BaseException(BaseResponseCode.USER_ALREADY_EXIST);
        User user = userRepository.save(User.toEntity(request));
        return LoginRes.toEntity(jwtUtil.issuedAccessToken(user.getId()));
    }

    // 로그아웃

    // 회원탈퇴

    // 프로필 불러오기
    public GetProfileRes getProfile(User user) {
        return GetProfileRes.toDto(user);
    }

    @Transactional
    public void patchProfile(User user, PatchProfileReq request) {
        user.updateProfile(request);
    }

    // 프로필 편집

    // 포장마차 좋아요 목록 조회
    public List<GetStoreListRes> getStoreOfLike(User user, String category) {
        Stream<GetStoreListRes> likeStoreList = user.getStoreLikeList().stream()
                .map(sl -> GetStoreListRes.toDto(sl.getStore(), storeLikeRepository.getLikeCountByIsEnable(sl.getStore()), storeLikeRepository.existsByUserAndStoreAndIsEnable(user, sl.getStore(), true)));
        return storeService.sortStoreList(category, likeStoreList);
    }
}
