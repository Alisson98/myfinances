package github.com.Alisson98.myfinances.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Should not be Blank")
    @Size(min = 3, max = 30, message = "size must be between 3 and 20")
    String name;
    @Email(message = "invalid email!!")
    String email;
    @NotBlank(message = "Should not be Blank")
    @Size(min = 5, max = 20, message = "size must be between 3 and 20")
    String password;
}
