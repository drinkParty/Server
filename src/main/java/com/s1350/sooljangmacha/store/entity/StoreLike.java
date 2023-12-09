package com.s1350.sooljangmacha.store.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import com.s1350.sooljangmacha.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "store_id")
    private Store store;

    @Builder
    public StoreLike(User user, Store store) {
        this.user = user;
        this.store = store;
    }

    public static StoreLike toEntity(User user, Store store){
        return StoreLike.builder()
                .user(user)
                .store(store)
                .build();
    }

    public void changeValue(){
        this.setIsEnable(!this.getIsEnable());
    }
}
