package gentjanahani.u2w6d4.repository;

import gentjanahani.u2w6d4.entities.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, UUID> {
   
}
