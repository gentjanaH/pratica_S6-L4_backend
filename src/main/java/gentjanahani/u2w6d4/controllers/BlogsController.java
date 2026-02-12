package gentjanahani.u2w6d4.controllers;

import gentjanahani.u2w6d4.entities.Blogs;
import gentjanahani.u2w6d4.exceptions.ValidationException;
import gentjanahani.u2w6d4.payloads.NewBlogPayload;
import gentjanahani.u2w6d4.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    private final BlogsService blogsService;

    @Autowired
    public BlogsController(BlogsService blogsService) {
        this.blogsService = blogsService;
    }

    // 1. POST http://localhost:3023/blogs (+ Payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blogs createBlog(@RequestBody @Validated NewBlogPayload payload, BindingResult validationResult) {
        if(validationResult.hasErrors()){
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.blogsService.save(payload);
        }

    }

    //2. GET http://localhost:3023/blogs
    @GetMapping
    public Page<Blogs> getBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return this.blogsService.findBlogs(page, size);
    }

    // 3. GET http://localhost:3023/blogs/{idBlog}
    @GetMapping("/{idBlog}")
    public Blogs findById(@PathVariable UUID idBlog) {
        return this.blogsService.findById(idBlog);
    }

    // 4. PUT http://localhost:3023/blogs/{idBlog}
    @PutMapping("/{idBlog}")
    public Blogs findByIdAndUpdate(@PathVariable UUID idBlog, @RequestBody NewBlogPayload payload) {
        return this.blogsService.findByIdAndUpdate(idBlog, payload);
    }
}
