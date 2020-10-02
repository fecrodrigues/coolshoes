package br.com.fiap.coolshoes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        Date creationDate = getFromLocalDateTime(LocalDateTime.now());
        Date expirationDate = getFromLocalDateTime(LocalDateTime.now().plusMinutes(expire));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();

        return claims.getSubject();
    }

    private Date getFromLocalDateTime(LocalDateTime now) {
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }

}
