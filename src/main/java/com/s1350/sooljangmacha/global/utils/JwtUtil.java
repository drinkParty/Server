package com.s1350.sooljangmacha.global.utils;

import com.s1350.sooljangmacha.global.exception.BaseException;
import com.s1350.sooljangmacha.global.exception.BaseResponseCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static com.s1350.sooljangmacha.global.Constants.BEARER_PREFIX;
import static com.s1350.sooljangmacha.global.Constants.CLAIM_NAME;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long accessTokenExpiryTime = 1000L * 60 * 60 * 24 * 14; // 2주

    public static String replaceBearer(String header) {
        return header.substring(BEARER_PREFIX.length());
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
}
