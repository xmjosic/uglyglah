package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.SubuglyglahDto;
import hr.xmjosic.uglyglah.model.Subuglyglah;
import hr.xmjosic.uglyglah.repository.SubuglyglahRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class SubuglyglahService {

    private final SubuglyglahRepository subuglyglahRepository;

    @Autowired
    public SubuglyglahService(SubuglyglahRepository subuglyglahRepository) {
        this.subuglyglahRepository = subuglyglahRepository;
    }

    @Transactional
    public SubuglyglahDto save(SubuglyglahDto subuglyglahDto) {
        Subuglyglah save = subuglyglahRepository.save(mapSubuglyglahDto(subuglyglahDto));
        subuglyglahDto.setId(save.getId());
        return subuglyglahDto;
    }

    @Transactional(readOnly = true)
    public List<SubuglyglahDto> getAll() {
        return subuglyglahRepository.findAll().stream().map(this::mapToDto).collect(toList());
    }

    private SubuglyglahDto mapToDto(Subuglyglah subuglyglah) {
        return SubuglyglahDto.builder()
                .name(subuglyglah.getName())
                .id(subuglyglah.getId())
                .numOfPosts(subuglyglah.getPosts().size()).build();
    }

    private Subuglyglah mapSubuglyglahDto(SubuglyglahDto subuglyglahDto) {
        return Subuglyglah
                .builder()
                .name(subuglyglahDto.getName())
                .description(subuglyglahDto.getDescription())
                .build();
    }

}
