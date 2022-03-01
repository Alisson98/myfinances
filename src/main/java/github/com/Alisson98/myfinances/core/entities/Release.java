package github.com.Alisson98.myfinances.core.entities;


import github.com.Alisson98.myfinances.core.entities.enums.ReleaseStatus;
import github.com.Alisson98.myfinances.core.entities.enums.ReleaseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "releases", schema = "finances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Release {

    @Id
    @Column( name = "release_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long releaseId;

    @Column( name = "description")
    private String description;

    @Column( name = "month")
    private Integer month;

    @Column( name = "year")
    private Integer year;

    @Column( name = "value")
    private BigDecimal value;

    @Column( name = "type")
    @Enumerated(EnumType.STRING)
    private ReleaseType type;

    @Column( name = "status")
    @Enumerated(EnumType.STRING)
    private ReleaseStatus status;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

    @Column( name = "registration_date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate registrationDate;
}
