package com.s1350.sooljangmacha.store.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import com.s1350.sooljangmacha.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "store_id")
    private Store store;

    @NotNull
    @Size(max = 255)
    private String content;


    @Builder
    public StoreReview(User user, Store store, String content) {
        this.user = user;
        this.store = store;
        this.content = content;
    }

    public static StoreReview toEntity(User user, Store store, String content){
        return StoreReview.builder()
                .user(user)
                .store(store)
                .content(content)
                .build();
    }
}
