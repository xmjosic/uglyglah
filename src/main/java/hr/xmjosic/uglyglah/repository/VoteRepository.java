package hr.xmjosic.uglyglah.repository;

import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.User;
import hr.xmjosic.uglyglah.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
