package gentjanahani.u2w6d4.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewBlogPayload {
    @NotBlank(message = "La categoria è un campo obbligatorio")
    @Size(min=2, max=30, message = "Puoi inserire un numero di caratteri che va da un  minimo di 2 e massimo di 30")
    private String category;
    @NotBlank(message = "Il titolo è un campo obbligatorio")
    @Size(min=2, max=30, message = "Puoi inserire un numero di caratteri che va da un  minimo di 2 e massimo di 30")
    private String title;
    @NotBlank(message = "Il contenuto è un campo obbligatorio")
    @Size(min=2, max=30, message = "Puoi inserire un numero di caratteri che va da un  minimo di 5 e massimo di 300")
    private String content;
    @NotBlank(message = "Il tempo di lettura impiegato per leggere l'acquisto è un campo obbligatorio")
    @Min(5)
    private int readingTime;
    @NotBlank(message = "L'id dell'autore è campo obbligatorio")
    private String authorId;
}
