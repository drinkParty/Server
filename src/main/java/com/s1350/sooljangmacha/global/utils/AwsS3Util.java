package com.s1350.sooljangmacha.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AwsS3Util {

    public static String bucket;
    public static String region;

    @Value("${aws.s3.region}")
    public void setRegion(String value) {
        region = value;
    }

    @Value("${aws.s3.bucket}")
    public void setBucket(String value) {
        bucket = value;
    }

    public static String toUrl(String imgKey) {
        return imgKey == null ? null : "https://" + bucket + ".s3." + region + ".amazonaws.com/" + imgKey;
    }
}
