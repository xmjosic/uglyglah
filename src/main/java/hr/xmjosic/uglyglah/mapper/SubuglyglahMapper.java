package hr.xmjosic.uglyglah.mapper;

import hr.xmjosic.uglyglah.dto.SubuglyglahDto;
import hr.xmjosic.uglyglah.model.Post;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubuglyglahMapper {

    @Mapping(target = "numOfPosts", expression = "java(mapPosts(subuglyglah.getPosts()))")
    SubuglyglahDto mapSubuglyglahToDto(Subuglyglah subuglyglah);

    default Integer mapPosts(List<Post> numOfPosts) {
        return numOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subuglyglah mapDtoToSubuglyglah(SubuglyglahDto subuglyglahDto);
}
