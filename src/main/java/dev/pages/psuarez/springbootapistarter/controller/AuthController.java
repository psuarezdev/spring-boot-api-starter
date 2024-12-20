package dev.pages.psuarez.springbootapistarter.controller;

import dev.pages.psuarez.springbootapistarter.dto.auth.LoginDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.CreateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UserDTO;
import dev.pages.psuarez.springbootapistarter.service.AuthService;
import dev.pages.psuarez.springbootapistarter.service.JwtService;
import dev.pages.psuarez.springbootapistarter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final JwtService jwtService;
  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDto) {
    UserDTO userDto = authService.login(loginDto);

    if (userDto == null) {
      return ResponseEntity.badRequest()
        .body(Collections.singletonMap("message", "Wrong credentials"));
    }

    return ResponseEntity.ok(generateAuthResponse(userDto, true));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody CreateUserDTO createUserDto) {
    try {
      UserDTO userDto = authService.register(createUserDto);

      if (userDto == null) {
        return ResponseEntity.internalServerError()
          .body(Collections.singletonMap("message", "Error registering the user"));
      }

      return ResponseEntity.ok(generateAuthResponse(userDto, true));
    } catch (CredentialException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Refresh token is missing or invalid"));
    }

    String refreshToken = authHeader.substring(7);

    try {
      String email = jwtService.extractEmail(refreshToken);
      UserDTO userDto = userService.findByEmail(email).orElse(null);

      if (userDto == null || !jwtService.isTokenValid(refreshToken, userDto)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Collections.singletonMap("message", "Invalid refresh token"));
      }

      return ResponseEntity.ok(generateAuthResponse(userDto, false));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Collections.singletonMap("message", "Invalid or expired refresh token"));
    }
  }

  private HashMap<String, Object> generateAuthResponse(UserDTO userDto, boolean includeUser) {
    HashMap<String, Object> response = new HashMap<>();
    String accessToken = jwtService.generateToken(userDto);
    String refreshToken = jwtService.generateRefreshToken(userDto);
    if(includeUser) response.put("user", userDto);
    response.put("accessToken", accessToken);
    response.put("refreshToken", refreshToken);
    return response;
  }
}
