package hr.xmjosic.uglyglah.controller;

import hr.xmjosic.uglyglah.dto.SubuglyglahDto;
import hr.xmjosic.uglyglah.service.SubuglyglahService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subuglyglah")
@Slf4j
public class SubuglyglahController {

    private final SubuglyglahService subuglyglahService;

    @Autowired
    public SubuglyglahController(SubuglyglahService subuglyglahService) {
        this.subuglyglahService = subuglyglahService;
    }

    @PostMapping
    public ResponseEntity<SubuglyglahDto> createSubuglyglah(@RequestBody SubuglyglahDto subuglyglahDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subuglyglahService.save(subuglyglahDto));
    }

    @GetMapping
    public ResponseEntity<List<SubuglyglahDto>> getAllSubuglyglah() {
        return ResponseEntity.status(HttpStatus.OK).body(subuglyglahService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubuglyglahDto> getSubuglyglah(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subuglyglahService.getSubuglyglah(id));
    }
}
