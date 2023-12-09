package com.s1350.sooljangmacha.store.service;

import com.s1350.sooljangmacha.global.exception.BaseException;
import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import com.s1350.sooljangmacha.store.dto.request.PostStoreReq;
import com.s1350.sooljangmacha.store.dto.request.PostStoreReviewReq;
import com.s1350.sooljangmacha.store.dto.response.GetStoreListRes;
import com.s1350.sooljangmacha.store.dto.response.GetStoreRes;
import com.s1350.sooljangmacha.store.dto.response.GetStoreReviewRes;
import com.s1350.sooljangmacha.store.entity.Store;
import com.s1350.sooljangmacha.store.entity.StoreImg;
import com.s1350.sooljangmacha.store.entity.StoreLike;
import com.s1350.sooljangmacha.store.entity.StoreReview;
import com.s1350.sooljangmacha.store.repository.StoreImgRepository;
import com.s1350.sooljangmacha.store.repository.StoreLikeRepository;
import com.s1350.sooljangmacha.store.repository.StoreRepository;
import com.s1350.sooljangmacha.store.repository.StoreReviewRepository;
import com.s1350.sooljangmacha.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreLikeRepository storeLikeRepository;
    private final StoreImgRepository storeImgRepository;
    private final StoreReviewRepository storeReviewRepository;


    // 위치별 포장마차 전체 조회
    public GetStoreListRes getStores() {
        return null;
    }

    // 포장마차 상세 조회
    public GetStoreRes getStore(Long storeId) {
        Store store = storeRepository.findByIdAndIsEnable(storeId, true).orElseThrow(() -> new BaseException(BaseResponseCode.STORE_NOT_FOUND));
        return GetStoreRes.toDto(store, storeLikeRepository.getLikeCountByIsEnable());
    }

    // 포장마차 좋아요
    @Transactional
    public void postStoreLike(User user, Long storeId) {
        Store store = storeRepository.findByIdAndIsEnable(storeId, true).orElseThrow(() -> new BaseException(BaseResponseCode.STORE_NOT_FOUND));
        storeLikeRepository.findByUserAndStore(user, store)
                .ifPresentOrElse(
                        sl -> {
                           sl.changeValue();
                           storeLikeRepository.save(sl);
                        },
                        () -> storeLikeRepository.save(StoreLike.toEntity(user, store))
                );
    }

    // 포장마차 등록
    @Transactional
    public void postStore(PostStoreReq req) {
        // 기획관련 한 번 더 물어보기 (예외처리)
        if(storeRepository.existsByXAndYAndIsEnable(req.getX(), req.getY(), true)) throw new BaseException(BaseResponseCode.EXISTS_STORE);
        // 저장
        Store store = Store.toEntity(req);
        storeRepository.save(store);
        storeImgRepository.saveAll(StoreImg.toEntityList(req.getImgUrls(), store));
    }

    // 포장마차 후기 조회
    public List<GetStoreReviewRes> getStoreReview(Long storeId) {
        Store store = storeRepository.findByIdAndIsEnable(storeId, true).orElseThrow(() -> new BaseException(BaseResponseCode.STORE_NOT_FOUND));
        return store.getStoreReviewList().stream()
                .map(GetStoreReviewRes::toDto)
                .collect(Collectors.toList());
    }

    // 포장마차 후기 등록
    @Transactional
    public void postStoreReview(User user, Long storeId, @Valid PostStoreReviewReq req) {
        Store store = storeRepository.findByIdAndIsEnable(storeId, true).orElseThrow(() -> new BaseException(BaseResponseCode.STORE_NOT_FOUND));
        storeReviewRepository.save(StoreReview.toEntity(user, store, req.getContent()));
    }
}
