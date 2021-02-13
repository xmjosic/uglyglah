package hr.xmjosic.uglyglah.mapper;

import hr.xmjosic.uglyglah.dto.PostRequest;
import hr.xmjosic.uglyglah.dto.PostResponse;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import hr.xmjosic.uglyglah.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Post map(PostRequest postRequest, Subuglyglah subuglyglah, User user);

    @Mapping(target = "subuglyglahName", source = "subuglyglah.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "id", source = "postId")
    PostResponse mapToDto(Post post);

}
