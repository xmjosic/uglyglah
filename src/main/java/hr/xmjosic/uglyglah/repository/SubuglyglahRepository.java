package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Subuglyglah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubuglyglahRepository extends JpaRepository<Subuglyglah, Long> {
    Optional<Subuglyglah> findByName(String subuglyglahName);
}
