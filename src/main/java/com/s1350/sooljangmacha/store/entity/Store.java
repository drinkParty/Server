package com.s1350.sooljangmacha.store.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import lombok.AccessLevel;
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

    @OneToMany(mappedBy = "store")
    private List<StoreImg> storeImgList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<StoreLike> storeLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<StoreReview> storeReviewList = new ArrayList<>();

}
