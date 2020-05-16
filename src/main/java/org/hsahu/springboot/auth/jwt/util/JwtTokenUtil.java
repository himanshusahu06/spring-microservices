package org.hsahu.springboot.auth.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Jwt Token utility class to generate and validate JWT token
 * Jwt Claim is actual payload that needs to signed with a signature key
 */
@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${security.jwt.sign.key}")
    private String signatureKey;

    @Value("${security.jwt.expiry.ms}")
    private long tokenExpiryInMs;

    @PostConstruct
    public void postConstruct() {
        log.info("token expiry is set to {} milliseconds", tokenExpiryInMs);
    }

    public String getUsernameFromToken(String jwtToken) {
        return getClaimsFromJwtToken(jwtToken, Claims::getSubject);
    }

    private Date getExpirationFromToken(String jwtToken) {
        return getClaimsFromJwtToken(jwtToken, Claims::getExpiration);
    }

    private boolean tokenExpired(String jwtToken) {
        return getExpirationFromToken(jwtToken).before(new Date());
    }

    private <T> T getClaimsFromJwtToken(String jwtToken, Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(jwtToken).getBody();
        return claimsTFunction.apply(claims);
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiryInMs))
                .signWith(SignatureAlgorithm.HS256, signatureKey)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(); // currently with empty payload
        return generateToken(claims, userDetails.getUsername());
    }

    public boolean validToken(String jwtToken, UserDetails userDetails) {
        return getUsernameFromToken(jwtToken).equals(userDetails.getUsername()) && !tokenExpired(jwtToken);
    }
}
