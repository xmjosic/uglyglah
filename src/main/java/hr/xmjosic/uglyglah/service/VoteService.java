package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.VoteDto;
import hr.xmjosic.uglyglah.exceptions.PostNotFoundException;
import hr.xmjosic.uglyglah.exceptions.UglyglahException;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Vote;
import hr.xmjosic.uglyglah.model.VoteType;
import hr.xmjosic.uglyglah.repository.PostRepository;
import hr.xmjosic.uglyglah.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Autowired
    public VoteService(VoteRepository voteRepository, PostRepository postRepository, AuthService authService) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.authService = authService;
    }

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post with ID " + voteDto.getPostId() + " not found!"));

        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new UglyglahException("Already " + voteDto.getVoteType() + "'d for this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
