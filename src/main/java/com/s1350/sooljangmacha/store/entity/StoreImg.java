package com.s1350.sooljangmacha.store.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String imgKey;

    @ManyToOne
    @JoinColumn(nullable = false, name = "store_id")
    private Store store;

    @Builder
    public StoreImg(String imgKey, Store store) {
        this.imgKey = imgKey;
        this.store = store;
    }

    public static List<StoreImg> toEntityList(List<String> imgUrls, Store store){
        return imgUrls.stream()
                .map(i -> toEntity(store, i))
                .collect(Collectors.toList());
    }

    private static StoreImg toEntity(Store store, String i) {
        return StoreImg.builder().imgKey(i).store(store).build();
    }
}
