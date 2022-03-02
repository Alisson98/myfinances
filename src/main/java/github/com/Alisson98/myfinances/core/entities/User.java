package github.com.Alisson98.myfinances.core.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "user", schema = "finances")
@Data
@Builder
@NoArgsConstructor
public class User {
    @Id
    @Column( name = "user_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


}
