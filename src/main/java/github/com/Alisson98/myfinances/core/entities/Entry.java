package github.com.Alisson98.myfinances.core.entities;


import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import github.com.Alisson98.myfinances.core.entities.enums.EntryType;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "entry", schema = "finances")
public class Entry {

    @Id
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(name = "description")
    private String description;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EntryType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntryStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "registration_date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate registrationDate;

    public Entry() {
    }
    public Entry(Long entryId, String description, Integer month, Integer year, BigDecimal value, EntryType type, EntryStatus status, User user, LocalDate registrationDate) {
        setEntryId(entryId);
        setDescription(description);
        setMonth(month);
        setYear(year);
        setValue(value);
        setType(type);
        setStatus(status);
        setUser(user);
        setRegistrationDate(registrationDate);
    }



    public void setEntryId(Long entryId) {
        if (entryId == null) return;
        this.entryId = entryId;

    }

    public Long getEntryId() {
        return entryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
       this.registrationDate = (registrationDate == null)? LocalDate.now(): registrationDate;
    }
}
