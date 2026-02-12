package gentjanahani.u2w6d4.services;

import gentjanahani.u2w6d4.entities.Authors;
import gentjanahani.u2w6d4.entities.Blogs;
import gentjanahani.u2w6d4.exceptions.NotFoundException;
import gentjanahani.u2w6d4.payloads.NewBlogPayload;
import gentjanahani.u2w6d4.repository.BlogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BlogsService {

    private final BlogsRepository blogsRepository;
    private final AuthorsService authorsService;

    @Autowired
    public BlogsService(BlogsRepository blogsRepository, AuthorsService authorsService) {
        this.blogsRepository = blogsRepository;
        this.authorsService = authorsService;
    }

    public Blogs save(NewBlogPayload payload) {
        Authors authorFromDb = authorsService.findById(UUID.fromString(payload.getAuthorId()));


        Blogs newBlog = new Blogs(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), authorFromDb);

        newBlog.setCover("https://picsum.photos/200/300" + payload.getTitle() + "+" + payload.getAuthorId());

        Blogs savedBlog = this.blogsRepository.save(newBlog);

        log.info("Il blog con id " + savedBlog.getIdBlog() + " è stato salvato correttamente!");

        return savedBlog;
    }

    public Page<Blogs> findBlogs(int page, int size) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size);
        return this.blogsRepository.findAll(pageable);
    }


    public Blogs findById(UUID idBlog) {
        return this.blogsRepository.findById(idBlog)
                .orElseThrow(() -> new NotFoundException(idBlog));
    }

    public Blogs findByIdAndUpdate(UUID idBlog, NewBlogPayload payload) {

        Blogs found = this.findById(idBlog);

        found.setTitolo(payload.getTitle());
        found.setContenuto(payload.getContent());
        found.setCategoria(payload.getCategory());
        found.setTempoDiLettura(payload.getReadingTime());
        found.setCover("https://ui-avatars.com/api?name=" + payload.getTitle() + "+" + payload.getAuthorId());

        Blogs newBlog = this.blogsRepository.save(found);

        log.info("Il Blog con id " + newBlog.getIdBlog() + " è stato modificato correttamente");

        return newBlog;
    }

}
