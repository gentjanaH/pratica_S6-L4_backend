package gentjanahani.u2w6d4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Authors {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idAutore;

    private String name;
    private String surname;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Authors(String name, String surname, String email, LocalDate dataDiNascita) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dataDiNascita = dataDiNascita;

    }
}
