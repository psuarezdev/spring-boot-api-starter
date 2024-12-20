package dev.pages.psuarez.springbootapistarter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String avatar;
  private LocalDateTime createdAt;
}
