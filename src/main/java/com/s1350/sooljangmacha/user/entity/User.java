package com.s1350.sooljangmacha.user.entity;

import com.s1350.sooljangmacha.global.entity.BaseEntity;
import com.s1350.sooljangmacha.global.entityListener.UserEntityListener;
import com.s1350.sooljangmacha.global.utils.AwsS3Util;
import com.s1350.sooljangmacha.store.entity.StoreLike;
import com.s1350.sooljangmacha.user.dto.request.PatchProfileReq;
import com.s1350.sooljangmacha.user.dto.request.SignupReq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET is_enable = false, update_at = current_timestamp WHERE id = ?")
@EntityListeners(UserEntityListener.class)
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

    public static User toEntity(SignupReq request) {
        return User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .address(request.getAddress())
                .imgKey(request.getImgKey())
                .provider(Provider.valueOf(request.getProvider()))
                .build();
    }

    @Builder
    public User(String email, String nickname, String address, String imgKey, Provider provider) {
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.imgKey = imgKey;
        this.provider = provider;
    }

    public void updateProfile(PatchProfileReq request) {
        if (!Objects.equals(nickname, request.getNickname())) this.nickname = request.getNickname();
        if (!Objects.equals(AwsS3Util.toUrl(imgKey), request.getImgKey())) this.imgKey = request.getImgKey();
        if (!Objects.equals(address, request.getAddress())) this.address = request.getAddress();
    }
}
