package gentjanahani.u2w6d4.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Blogs {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idBlog;

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    //relazione ManyToOne
    @ManyToOne
    @JoinColumn(name = "author_id_autore")
    private Authors author;

    public Blogs(String categoria, String titolo, String contenuto, int tempoDiLettura, Authors author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }
}
