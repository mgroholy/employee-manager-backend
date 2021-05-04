package com.codecool.employeemanager.security;

import com.codecool.employeemanager.model.ClearanceLevel;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class JwtTokenServices {

    private String secretKey = "e455bfa8-7c7f-4b17-8bcd-aebffe258c6c";
    private final long VALIDITY = 36000000;
    private final String clearanceLevels = "clearanceLevels";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<ClearanceLevel> clearanceLevels){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(this.clearanceLevels, clearanceLevels);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + VALIDITY);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("jwt")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e){
            log.debug("JWT token invalid " + e);
        }
        return false;
    }

    public Authentication parseUserFromToken(String token) throws UsernameNotFoundException {
        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String email = body.getSubject();
        List<String> levels = (List<String>) body.get(clearanceLevels);
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for(String level: levels){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + level));
        }
        return new UsernamePasswordAuthenticationToken(email, "", authorities);


    }

}
