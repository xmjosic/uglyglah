package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.CommentsDto;
import hr.xmjosic.uglyglah.exceptions.PostNotFoundException;
import hr.xmjosic.uglyglah.mapper.CommentMapper;
import hr.xmjosic.uglyglah.model.Comment;
import hr.xmjosic.uglyglah.model.NotificationEmail;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.User;
import hr.xmjosic.uglyglah.repository.CommentRepository;
import hr.xmjosic.uglyglah.repository.PostRepository;
import hr.xmjosic.uglyglah.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private static final String POST_URL = "";

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    @Autowired
    public CommentService(PostRepository postRepository, UserRepository userRepository, AuthService authService, CommentMapper commentMapper, CommentRepository commentRepository, MailContentBuilder mailContentBuilder, MailService mailService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.mailContentBuilder = mailContentBuilder;
        this.mailService = mailService;
    }

    public void save(CommentsDto commentsDto) {

        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post ID: " + commentsDto.getPostId() + " not found!"));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on  your post." + POST_URL);
        sendCommentNotidication(message, post.getUser());
    }

    private void sendCommentNotidication(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post " + postId + " not found!"));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User " + userName + " not found!"));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
