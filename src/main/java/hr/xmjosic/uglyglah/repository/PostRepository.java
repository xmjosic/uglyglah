package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import hr.xmjosic.uglyglah.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubuglyglah(Subuglyglah subuglyglah);

    List<Post> findByUser(User user);
}
