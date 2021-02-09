package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Subuglyglah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubuglyglahRepository extends JpaRepository<Subuglyglah, Long> {
}
