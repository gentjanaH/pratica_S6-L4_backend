package gentjanahani.u2w6d4.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class NewAuthorPayload {
    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min=2, max=30, message = "Il nome deve avere minimo 2 caretteri e massimo 30")
    private String name;
    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(min=2, max=30, message = "Il cognome deve avere minimo 2 caretteri e massimo 30")
    private String surname;
    @NotBlank(message="L'email è obbligatoria")
    @Email(message = "L'indirizzo fornito non è corretto.")
    private String email;
    @NotNull(message = "La data di nascita è obbligatoria")
    @Past(message = "La data di nascita deve essere nel passato")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}
