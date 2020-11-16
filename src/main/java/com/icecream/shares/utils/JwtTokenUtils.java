package com.icecream.shares.utils;


import com.winterice.vote.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang.time.DateUtils;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtTokenUtils {


    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    public static String createToken(String username, List<String> roles, boolean remember) {
        //long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        long expiration = SecurityConstants.EXPIRATION;
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles))
                .setIssuer("ForestjClimb")
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(DateUtils.addDays(new Date(),remember?30:1))
                .compact();

        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
    }

    public static String createTempToken(String username) {
        //long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        long expiration = SecurityConstants.EXPIRATION;
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuer("ForestjClimb")
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(DateUtils.addDays(new Date(),1))
                .compact();
        return tokenPrefix;
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取用户所有角色
     */
    public static List<String> getUserRolesByToken(String token) {
        Claims tokenBody = getTokenBody(token);
        String role = (String) tokenBody
                .get(SecurityConstants.ROLE_CLAIMS);
        System.out.println(role);
        return Arrays.stream(role.split(",")).collect(Collectors.toList());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Date getIssuedAtByToken(String token){
        return getTokenBody(token).getIssuedAt();
    }
}
