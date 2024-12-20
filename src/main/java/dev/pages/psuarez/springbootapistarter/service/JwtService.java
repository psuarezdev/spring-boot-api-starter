package dev.pages.psuarez.springbootapistarter.service;

import dev.pages.psuarez.springbootapistarter.dto.user.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  // You can set up a different secret key for the refresh tokens
  // in case you want more security and separation of responsibilities
  @Value("${security.jwt.secret-key}")
  private String secretKey;

  @Getter
  @Value("${security.jwt.expiration}")
  private long jwtExpiration;

  @Getter
  @Value("${security.jwt.refresh-token.expiration}")
  private long refreshJwtExpiration;

  public String generateToken(UserDTO userDto) {
    return buildToken(userDto, jwtExpiration);
  }

  public String generateRefreshToken(UserDTO userDto) {
    return buildToken(userDto, refreshJwtExpiration);
  }

  public String buildToken(UserDTO userDto, long expiration) {
    return Jwts.builder()
      .setId(userDto.getId().toString())
      .setSubject(userDto.getEmail())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDTO userDto) {
    final String email = extractEmail(token);
    return (email.equals(userDto.getEmail())) && !isTokenExpired(token);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String email = extractEmail(token);
    return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}