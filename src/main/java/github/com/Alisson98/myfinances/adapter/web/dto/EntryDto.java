package github.com.Alisson98.myfinances.adapter.web.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EntryDto {
    @NotNull(message = "Should not be Null")
    @NotBlank(message = "Should not be Blank")
    private String description;

    @NotNull(message = "Should not be Null")
    @Min(value = 1, message = "Must be greater than or equal to 1")
    @Max(value = 12, message = "Must be less than or equal to 12")
    private Integer month;

    @NotNull(message = "Should not be Null")
    @Min(value = 2010, message = "The system only supports dates than greater/equal to 2010")
    @Max(value = 2040,  message = "The system only supports dates than less/equal to 2040")
    private Integer year;

    @NotNull(message = "Should not be Null")
    @Min(value = 0L, message = "Must be enter some value")
    private BigDecimal value;

    @NotNull(message = "Should not be Null")
    private Long userId;

    @NotNull(message = "Should not be Null")
    @NotBlank(message = "Should not be Blank")
    private String type;

    @NotNull(message = "Should not be Null")
    @NotBlank(message = "Should not be Blank")
    private String status;

    private LocalDate registrationDate;

}
