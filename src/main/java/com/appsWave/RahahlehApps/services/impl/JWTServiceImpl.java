package com.appsWave.RahahlehApps.services.impl;

import com.appsWave.RahahlehApps.entities.User;
import com.appsWave.RahahlehApps.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    public static final String PRIVATE_KEY = "xw8DNmU5Y0Q1nf34E4JyPrMU2zHzAfRbM0duedDOMls";

    /**
     * We use user email as a subject for userDetails object,
     * then I sign it using algorithim HS256
     * and a private key getSignKey()
     */
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(SignatureAlgorithm.HS256,getSignKey() )
                .compact();
    }

    /**
     * Claims are values saved into token like user name, audiance, expiration date and etc..
     */
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolveres){
        final Claims claims=extractAllClaims(token);
        return claimsResolveres.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Base64.getDecoder().decode(PRIVATE_KEY);
        return Keys.hmacShaKeyFor(key);

    }
    // Here user email is his username
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        return  Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * We do check the token validation using the algorithm to read all claims
     * Then we extrct the user name from the claim
     * Then we do check if user is correct and not expired.
     */
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     *  similar to generate token but we add an option to add extra claims and we extend the expiration date
     */
    @Override
    public String generateRefreshToken(HashMap<String, Object> extraClaims, User userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24 * 7))
                .signWith(SignatureAlgorithm.HS256,getSignKey() )
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
