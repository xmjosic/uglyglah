package hr.xmjosic.uglyglah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    @NotEmpty(message = "email is required!")
    private String email;

    @NotBlank(message = "username is required!")
    private String username;

    @NotBlank(message = "password is required!")
    private String password;
}
