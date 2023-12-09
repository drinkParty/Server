package com.s1350.sooljangmacha.store.dto.response;

import com.s1350.sooljangmacha.store.entity.StoreReview;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetStoreReviewRes {
    @Schema(type = "String", description = "상점 후기", example = "화장실이 멀어요ㅜ")
    private String content;
    @Schema(type = "String", description = "후기 작성 날짜", example = "술장마차1호점")
    private String date;

    public static GetStoreReviewRes toDto(StoreReview review){
        return GetStoreReviewRes.builder()
                .content(review.getContent())
                .date(review.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
    }
}
