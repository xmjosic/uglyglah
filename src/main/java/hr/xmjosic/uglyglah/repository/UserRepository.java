package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
