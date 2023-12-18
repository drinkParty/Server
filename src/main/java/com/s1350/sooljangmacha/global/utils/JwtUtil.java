package com.s1350.sooljangmacha.global.utils;

import com.s1350.sooljangmacha.global.exception.BaseException;
import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.s1350.sooljangmacha.global.Constants.*;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long accessTokenExpiryTime = 1000L * 60 * 60 * 24 * 14; // 2주
    private final RedisTemplate<String, String> redisTemplate;

    public static String replaceBearer(String header) {
        try {
            return header.substring(BEARER_PREFIX.length());
        } catch (DecodingException e) {
            throw new BaseException(BaseResponseCode.HEADER_DECODE_ERROR);
        }
    }


    public boolean validateToken(String token) {
        try {
            getBody(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException e) {
            throw new BaseException(BaseResponseCode.INVALID_TOKEN);
        } catch (MalformedJwtException e) {
            throw new BaseException(BaseResponseCode.MALFORMED_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new BaseException(BaseResponseCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new BaseException(BaseResponseCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new BaseException(BaseResponseCode.NULL_TOKEN);
        }
    }

    private Key getSigningKey() {
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long getJwtContents(String token) {
        String userId = String.valueOf(getBody(token).get(CLAIM_NAME));
        return Long.parseLong(userId);
    }

    private Claims getBody(String token) {
        try {
            if(StringUtils.hasText(redisTemplate.opsForValue().get(token))) throw new BaseException(BaseResponseCode.EXPIRED_TOKEN);
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String issuedAccessToken(Long userId) {
        return issuedToken("access_token", accessTokenExpiryTime, String.valueOf(userId));
    }

    public String issuedToken(String tokenName, long expiryTime, String userId) {
        final Date now = new Date();

        final Claims claims = Jwts.claims()
                .setSubject(tokenName)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiryTime));

        claims.put(CLAIM_NAME, userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public void logout(String token, Long userId) {
        setValueOfRedis(token, LOGOUT, getExpiration(token), TimeUnit.MILLISECONDS);
        deleteToken(String.valueOf(userId));
    }

    private void setValueOfRedis(String key, String value, Long expiration, TimeUnit time) {
        redisTemplate.opsForValue().set(key, value, expiration, time);
    }

    private Long getExpiration(String token) {
        Date expiration = getBody(token).getExpiration();
        return expiration.getTime() - new Date().getTime();
    }

    private void deleteToken(String userId) {
        if (redisTemplate.opsForValue().get(userId) != null) redisTemplate.delete(userId);
    }
}
