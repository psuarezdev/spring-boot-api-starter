package dev.pages.psuarez.springbootapistarter.service;

import dev.pages.psuarez.springbootapistarter.dto.auth.LoginDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.CreateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UserDTO;
import dev.pages.psuarez.springbootapistarter.model.User;
import dev.pages.psuarez.springbootapistarter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public UserDTO login(LoginDTO loginDto) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          loginDto.getEmail(),
          loginDto.getPassword()
        )
      );

      return userService.findByEmail(loginDto.getEmail()).orElse(null);
    } catch (AuthenticationException e) {
      return null;
    }
  }

  public UserDTO register(CreateUserDTO createUserDto) throws CredentialException {
    User user = userRepository.findByEmail(createUserDto.getEmail()).orElse(null);
    if(user != null) throw new CredentialException("User already exists");
    return userService.save(createUserDto);
  }
}
