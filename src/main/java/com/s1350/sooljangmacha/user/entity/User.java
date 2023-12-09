package com.s1350.sooljangmacha.user.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import com.s1350.sooljangmacha.store.entity.StoreLike;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 30)
    private String nickname;

    @NotNull
    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String imgKey;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "user")
    private List<StoreLike> storeLikeList = new ArrayList<>();

}
