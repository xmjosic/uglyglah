package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.PostRequest;
import hr.xmjosic.uglyglah.dto.PostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostService {
    public void save(PostRequest postRequest) {

    }

    public PostResponse getPost(Long id) {
        return null;
    }

    public List<PostResponse> getAllPosts() {
        return null;
    }

    public List<PostResponse> getPostsBySubuglyglah(Long id) {
        return null;
    }

    public List<PostResponse> getPostsByUsername(String username) {
        return null;
    }
}
