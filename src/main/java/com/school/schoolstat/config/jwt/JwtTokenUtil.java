package com.school.schoolstat.config.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${security.jwt.secret}")
    private String JWT_SECRET;

    @Value("${security.jwt.token.expiration}")
    private long JWT_TOKEN_VALIDITY;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public List<String> getRolesFormToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        List<String> roles = (List<String>) claims.get("roles");
        return roles;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    private Boolean ignoreTokenExpiration(String token) {
//        // here you specify tokens, for that the expiration is ignored
//        return false;
//    }

    public String generateToken(UserDetails userDetails, String UserId) {
        Claims claims = Jwts.claims();
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(r -> {
            roles.add(r.getAuthority());
        });
        claims.put("roles", roles);
        claims.put("UserId", UserId);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Claims claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    /*
     * public Boolean validateToken(String token, UserDetails userDetails) { final
     * String username = getUsernameFromToken(token); return
     * (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); }
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }
}
