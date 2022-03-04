package github.com.Alisson98.myfinances.adapter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardErrorDto {
    Integer status;
    String error;
}
