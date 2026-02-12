package gentjanahani.u2w6d4.repository;

import gentjanahani.u2w6d4.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, UUID> {

    Optional<Authors> findByEmail(String email);


    Authors findByIdAutore(UUID idAutore);

}
