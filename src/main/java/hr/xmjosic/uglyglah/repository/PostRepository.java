package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
