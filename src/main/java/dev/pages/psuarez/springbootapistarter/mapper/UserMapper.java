package dev.pages.psuarez.springbootapistarter.mapper;

import dev.pages.psuarez.springbootapistarter.dto.user.CreateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UpdateUserDTO;
import dev.pages.psuarez.springbootapistarter.dto.user.UserDTO;
import dev.pages.psuarez.springbootapistarter.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
  private final ModelMapper modelMapper;

  public UserDTO toDto(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public User toEntity(UserDTO userDto) {
    return modelMapper.map(userDto, User.class);
  }

  public User toEntity(CreateUserDTO createUserDto) {
    return modelMapper.map(createUserDto, User.class);
  }

  public User toEntity(UpdateUserDTO updateUserDto) {
    return modelMapper.map(updateUserDto, User.class);
  }
}
