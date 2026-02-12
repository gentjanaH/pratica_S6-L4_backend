package gentjanahani.u2w6d4.exceptions;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMessages=new ArrayList<>();

    public ValidationException(List<String> errorsMessages) {
        super("Errore nel payload");
        this.errorsMessages=errorsMessages;

    }
}
