package github.com.Alisson98.myfinances.core.entities;


import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import github.com.Alisson98.myfinances.core.entities.enums.EntryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "entry", schema = "finances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {

    @Id
    @Column( name = "entry_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long entryId;

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
    private EntryType type;

    @Column( name = "status")
    @Enumerated(EnumType.STRING)
    private EntryStatus status;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

    @Column( name = "registration_date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate registrationDate;
}
