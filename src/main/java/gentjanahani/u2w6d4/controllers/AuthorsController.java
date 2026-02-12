package gentjanahani.u2w6d4.controllers;

import gentjanahani.u2w6d4.entities.Authors;
import gentjanahani.u2w6d4.exceptions.BadRequestException;
import gentjanahani.u2w6d4.exceptions.ValidationException;
import gentjanahani.u2w6d4.payloads.NewAuthorPayload;
import gentjanahani.u2w6d4.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }


    // 1. POST http://localhost:3023/authors (+ Payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authors createAuthor(@RequestBody @Validated NewAuthorPayload payload, BindingResult validationResult) {

        if(validationResult.hasErrors()){
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.authorsService.save(payload);
        }
    }

    //2. GET http://localhost:3023/authors
    @GetMapping
    public Page<Authors> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return this.authorsService.findAuthors(page, size);
    }

    // 3. GET http://localhost:3023/authors/{idBlog}
    @GetMapping("/{idAutore}")
    public Authors findById(@PathVariable UUID idAutore) {
        return this.authorsService.findById(idAutore);
    }

    // 4. PUT http://localhost:3023/authors/{idAutore}
    @PutMapping("/{idAutore}")
    public Authors findByIdAndUpdate(@PathVariable UUID idAutore, @RequestBody NewAuthorPayload payload) {
        return this.authorsService.findByIdAndUpdate(idAutore, payload);
    }


    // 5. DELETE http://localhost:3002/authors/{idAutore}
    @DeleteMapping("/{idAutore}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID idAutore) {
        this.authorsService.findByIdAndDelete(idAutore);
    }

}
