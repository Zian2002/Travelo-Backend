package com.zian.travelo.security.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService<T> {
    private final String jwt_secret = "BuiNhutDuy";
    private final long jwt_expiration = 86400000L;

    public String generateToken(T data){
        long currentTime =System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(data.toString())
                .claim("data", data)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + jwt_expiration))
                .signWith(SignatureAlgorithm.HS256, jwt_secret)
                .compact();
    }

    public String getEmailFromToken(String token){
        if (!validateToken(token))
            return null;
        Claims claims = Jwts.parser()
                .setSigningKey(jwt_secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwt_secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public Boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            log.error("Unsupported JWT token");
        }catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }

}
