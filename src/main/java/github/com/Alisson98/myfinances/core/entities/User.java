package github.com.Alisson98.myfinances.core.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "user", schema = "finances")
@Data
public class User {
    @Id
    @Column( name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


}
