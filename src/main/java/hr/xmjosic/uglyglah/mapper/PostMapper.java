package hr.xmjosic.uglyglah.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import hr.xmjosic.uglyglah.dto.PostRequest;
import hr.xmjosic.uglyglah.dto.PostResponse;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import hr.xmjosic.uglyglah.model.User;
import hr.xmjosic.uglyglah.repository.CommentRepository;
import hr.xmjosic.uglyglah.repository.VoteRepository;
import hr.xmjosic.uglyglah.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AuthService authService;


    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subuglyglah", source = "subuglyglah")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subuglyglah subuglyglah, User user);

    @Mapping(target = "subuglyglahName", source = "subuglyglah.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}
