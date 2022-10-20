package com.rolandleou.service;

import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rolandleou.model.StockUser;
import com.rolandleou.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	 private final String KEY = "StockAPIStockAPIStockAPIStockAPIStockAPIStockAPI";
	 
	 public String generateToken(User user) {
	        Authentication authentication =
            new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
	        authentication = authenticationManager.authenticate(authentication);
	        StockUser userDetails = (StockUser) authentication.getPrincipal();

	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MINUTE, 20);

	        Claims claims = Jwts.claims();
		    claims.put("user_id", userDetails.getUser().getId());
			claims.put("account", userDetails.getUsername());
			claims.put("name", userDetails.getUser().getName());
			claims.put("authority", userDetails.getUser().getAuthority());
	        claims.setExpiration(calendar.getTime());
	        claims.setIssuer("KensStockAPI");

	        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

	        return Jwts.builder()
	                .setClaims(claims)
	                .signWith(secretKey)
	                .compact();
	    }
	 
	 public Map<String, Object> parseToken(String token) {
	        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

	        JwtParser parser = Jwts.parserBuilder()
	                .setSigningKey(secretKey)
	                .build();

	        Claims claims = parser
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.entrySet().stream()
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	    }

}
