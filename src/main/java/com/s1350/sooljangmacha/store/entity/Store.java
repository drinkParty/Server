package com.s1350.sooljangmacha.store.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import com.s1350.sooljangmacha.store.dto.request.PostStoreReq;
import com.s1350.sooljangmacha.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String x;

    @NotNull
    private String y;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 255)
    private String address;

    @Size(max = 50)
    private String phone;

    @Size(max = 255)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<StoreImg> storeImgList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<StoreLike> storeLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<StoreReview> storeReviewList = new ArrayList<>();

    @Builder
    public Store(String x, String y, String name, String address, String phone, String content, User user) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.content = content;
        this.user = user;
    }

    public static Store toEntity(User user, PostStoreReq req){
        return Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                .x(req.getX())
                .y(req.getY())
                .content(req.getContent())
                .phone(req.getPhone())
                .user(user)
                .build();
    }

}
