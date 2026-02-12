package gentjanahani.u2w6d4.services;

import gentjanahani.u2w6d4.entities.Authors;
import gentjanahani.u2w6d4.exceptions.BadRequestException;
import gentjanahani.u2w6d4.exceptions.NotFoundException;
import gentjanahani.u2w6d4.payloads.NewAuthorPayload;
import gentjanahani.u2w6d4.repository.AuthorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Authors save(NewAuthorPayload payload) {
        this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
        });

        Authors newAuthor = new Authors(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getDateOfBirth());
        newAuthor.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

        Authors savedAuthor = this.authorsRepository.save(newAuthor);

        log.info("L'autore con id " + savedAuthor.getIdAutore() + " è stato salvato correttamente!");

        return savedAuthor;
    }

    public Authors findById(UUID idAutore) {
        Authors autore = authorsRepository.findByIdAutore(idAutore);
        if (autore == null) throw new NotFoundException(idAutore);
        return autore;

    }

    public Page<Authors> findAuthors(int page, int size) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size);
        return this.authorsRepository.findAll(pageable);
    }

    public Authors findByIdAndUpdate(UUID idAutore, NewAuthorPayload payload) {

        Authors found = this.findById(idAutore);

        if (!found.getEmail().equals(payload.getEmail()))
            this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
                throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
            });

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setDataDiNascita(payload.getDateOfBirth());
        found.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());

        Authors newAuthor = this.authorsRepository.save(found);

        log.info("L'autore con id " + newAuthor.getIdAutore() + " è stato modificato correttamente");

        return newAuthor;
    }


    public void findByIdAndDelete(UUID idAutore) {
        Authors found = this.findById(idAutore);
        this.authorsRepository.delete(found);
    }
}
