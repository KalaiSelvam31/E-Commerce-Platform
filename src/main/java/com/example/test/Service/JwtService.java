package com.example.test.Service;

import com.example.test.Model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String Secretkey="KLvvlrPBxn9CE3Z23BdkcBQm0h0Wz6Tp";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(Secretkey.getBytes(StandardCharsets.UTF_8));



    public String generateToken(Users user){

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(secretKey,Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // secretKey = HMAC key
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // This returns the username
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(secretKey) // HS256 key (SecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expiration.before(new Date());
    }


}


