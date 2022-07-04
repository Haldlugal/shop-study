package ru.gb.gbapimay.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.gb.gbapimay.security.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldMatch(firstFieldName = "password", secondFieldName = "matchingPassword", message = "The passwords must match")
public class UserDto {
    @JsonIgnore
    private Long id;

    @NotBlank
    @Size(min = 3, message = "username length must be greater than 3 symbols")
    private String username;
    @NotBlank
    @Size(min = 8, message = "password length must be greater than 8 symbols")
    private String password;
    @NotNull(message = "is required")
    @Size(min = 8, message = "password length must be greater than 8 symbols")
    private String matchingPassword;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 5, max = 20)
    private String phone;
}