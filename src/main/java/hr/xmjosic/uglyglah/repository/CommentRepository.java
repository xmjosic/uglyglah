package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
