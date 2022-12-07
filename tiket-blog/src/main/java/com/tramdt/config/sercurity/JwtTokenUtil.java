package com.tramdt.config.sercurity;

import com.tramdt.model.Customer;
import com.tramdt.security.userprinciple.AccountPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private String jwtSecret = "tramdt";
    private int jwtExpiration = 86400000;
    public String createToken(Authentication authentication){
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        String token = Jwts.builder().setSubject(accountPrinciple.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
        return token;
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public String getEmailFromToken(String token){
        String email = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return email;
    }


}
