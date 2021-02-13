package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.PostRequest;
import hr.xmjosic.uglyglah.dto.PostResponse;
import hr.xmjosic.uglyglah.exceptions.PostNotFoundException;
import hr.xmjosic.uglyglah.exceptions.SubuglyglahNotFoundException;
import hr.xmjosic.uglyglah.mapper.PostMapper;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import hr.xmjosic.uglyglah.model.User;
import hr.xmjosic.uglyglah.repository.PostRepository;
import hr.xmjosic.uglyglah.repository.SubuglyglahRepository;
import hr.xmjosic.uglyglah.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
public class PostService {

    private final SubuglyglahRepository subuglyglahRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(SubuglyglahRepository subuglyglahRepository, AuthService authService, PostMapper postMapper, PostRepository postRepository, UserRepository userRepository) {
        this.subuglyglahRepository = subuglyglahRepository;
        this.authService = authService;
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void save(PostRequest postRequest) {
        Subuglyglah subuglyglah = subuglyglahRepository.findByName(postRequest.getSubuglyglahName())
                .orElseThrow(() -> new SubuglyglahNotFoundException("Post " + postRequest.getSubuglyglahName() + " not found!"));

        postRepository.save(postMapper.map(postRequest, subuglyglah, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post " + id + " not found!"));

        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubuglyglah(Long id) {
        Subuglyglah subuglyglah = subuglyglahRepository.findById(id)
                .orElseThrow(() -> new SubuglyglahNotFoundException("Subuglyglah " + id + " not found!"));
        List<Post> posts = postRepository.findAllBySubuglyglah(subuglyglah);
        return posts.stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
