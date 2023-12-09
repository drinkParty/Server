package com.s1350.sooljangmacha.global.entityListener;

import com.s1350.sooljangmacha.global.utils.BeanUtil;
import com.s1350.sooljangmacha.store.repository.StoreLikeRepository;
import com.s1350.sooljangmacha.store.repository.StoreReviewRepository;
import com.s1350.sooljangmacha.user.entity.User;

import javax.persistence.PreRemove;

public class UserEntityListener {
    @PreRemove
    public void onUpdate(User user) {
        StoreLikeRepository storeLikeRepository = BeanUtil.getBean(StoreLikeRepository.class);
        storeLikeRepository.deleteByUser(user);
        StoreReviewRepository storeReviewRepository = BeanUtil.getBean(StoreReviewRepository.class);
        storeReviewRepository.deleteByUser(user);
    }
}
