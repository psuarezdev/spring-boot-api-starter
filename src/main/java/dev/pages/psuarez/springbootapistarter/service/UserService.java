package dev.pages.psuarez.springbootapistarter.service;

import dev.pages.psuarez.springbootapistarter.dto.user.CreateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UpdateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UserDTO;
import dev.pages.psuarez.springbootapistarter.mapper.UserMapper;
import dev.pages.psuarez.springbootapistarter.model.User;
import dev.pages.psuarez.springbootapistarter.model.UserRole;
import dev.pages.psuarez.springbootapistarter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public List<UserDTO> findAll() {
    return userRepository.findAll()
      .stream()
      .map(userMapper::toDto)
      .toList();
  }

  public Optional<UserDTO> findById(Long id) {
    return userRepository.findById(id).map(userMapper::toDto);
  }

  public Optional<UserDTO> findByEmail(String email) {
    return userRepository.findByEmail(email).map(userMapper::toDto);
  }

  public UserDTO save(CreateUserDTO createUserDTO) {
    String passwordHash = passwordEncoder.encode(createUserDTO.getPassword());
    createUserDTO.setPassword(passwordHash);
    createUserDTO.setRoles(Set.of(UserRole.USER));
    User userCreadted = userRepository.save(userMapper.toEntity(createUserDTO));
    return userMapper.toDto(userCreadted);
  }

  public UserDTO edit(UpdateUserDTO updateUserDTO) {
    Optional<User> userExists = userRepository.findById(updateUserDTO.getId());
    if(userExists.isEmpty()) return null;
    User userUpdated = userRepository.save(userMapper.toEntity(updateUserDTO));
    return userMapper.toDto(userUpdated);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
