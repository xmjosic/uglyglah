package hr.xmjosic.uglyglah.service;

import hr.xmjosic.uglyglah.dto.SubuglyglahDto;
import hr.xmjosic.uglyglah.exceptions.UglyglahException;
import hr.xmjosic.uglyglah.mapper.SubuglyglahMapper;
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
    private final SubuglyglahMapper subuglyglahMapper;

    @Autowired
    public SubuglyglahService(SubuglyglahRepository subuglyglahRepository, SubuglyglahMapper subuglyglahMapper) {
        this.subuglyglahRepository = subuglyglahRepository;
        this.subuglyglahMapper = subuglyglahMapper;
    }

    @Transactional
    public SubuglyglahDto save(SubuglyglahDto subuglyglahDto) {
        Subuglyglah save = subuglyglahRepository.save(subuglyglahMapper.mapDtoToSubuglyglah(subuglyglahDto));
        subuglyglahDto.setId(save.getId());
        return subuglyglahDto;
    }

    @Transactional(readOnly = true)
    public List<SubuglyglahDto> getAll() {
        return subuglyglahRepository.findAll().stream().map(subuglyglahMapper::mapSubuglyglahToDto).collect(toList());
    }


    public SubuglyglahDto getSubuglyglah(Long id) {
        Subuglyglah subuglyglah = subuglyglahRepository.findById(id)
                .orElseThrow(() -> new UglyglahException("No subuglyglah found whit id " + id));

        return subuglyglahMapper.mapSubuglyglahToDto(subuglyglah);
    }
}
