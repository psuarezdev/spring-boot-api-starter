package dev.pages.psuarez.springbootapistarter.dto.user;

import dev.pages.psuarez.springbootapistarter.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
public class CreateUserDTO {
  @NotBlank(message = "FirstName is required")
  @Length(min = 3, message = "FirstName must have at least 3 characters")
  private String firstName;

  @NotBlank(message = "LastName is required")
  @Length(min = 3, message = "LastName must have at least 3 characters")
  private String lastName;

  @NotBlank(message = "Email is required")
  @Email(message = "Email does not have a valid format")
  private String email;

  @NotBlank(message = "Password is required")
  @Length(min = 6, message = "Pasword must have at least 6 characters")
  private String password;

  private String avatar;

  private Set<UserRole> roles;
}
