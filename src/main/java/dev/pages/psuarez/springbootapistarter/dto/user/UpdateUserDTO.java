package dev.pages.psuarez.springbootapistarter.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateUserDTO {
  @NotNull(message = "Id is required")
  @Min(value = 1, message = "Id must be greater than 0")
  private Long id;

  @NotBlank(message = "FirstName is required")
  @Length(min = 3, message = "FirstName must have at least 3 characters")
  private String firstName;

  @NotBlank(message = "LastName is required")
  @Length(min = 3, message = "LastName must have at least 3 characters")
  private String lastName;

  @NotBlank(message = "Email is required")
  @Email(message = "Email does not have a valid format")
  private String email;

  private String avatar;
}
