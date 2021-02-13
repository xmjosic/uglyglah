package hr.xmjosic.uglyglah.controller;

import hr.xmjosic.uglyglah.dto.PostRequest;
import hr.xmjosic.uglyglah.dto.PostResponse;
import hr.xmjosic.uglyglah.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/")
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/by-subuglyglah/{id}")
    public List<PostResponse> getPostsBySubuglyglah(@PathVariable Long id) {
        return postService.getPostsBySubuglyglah(id);
    }

    @GetMapping("/by-user/{name}")
    public List<PostResponse> getPostsByUsername(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }
}
