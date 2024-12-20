package dev.pages.psuarez.springbootapistarter.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class LoginDTO {
  @NotBlank(message = "Email is required")
  @Email(message = "Email does not have a valid format")
  private String email;

  @NotBlank(message = "Password is required")
  @Length(min = 6, message = "Pasword must have at least 6 characters")
  private String password;
}
