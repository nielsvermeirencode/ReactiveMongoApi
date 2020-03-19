package be.nielsvermeiren.snippet;

import be.nielsvermeiren.tag.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/snippet")
public class SnippetController {

    private final SnippetRepository repository;
    private final TechnologyRepository techRepository;

    @GetMapping
    Flux<Snippet> findAll () {
        return repository.findAll();
    }

    @GetMapping("/{name}")
    Mono<Snippet> findByName(@PathVariable String name) {
        return repository.findById(name);
    }

    @GetMapping("/lang/{lang}")
    Flux<Snippet> findByLanguage(@PathVariable String lang) {
        return repository.findAllByLanguage(Language.valueOf(lang));
    }

    @PostMapping
    Mono<Snippet> save(@RequestBody Snippet snippet) {
        return repository.save(snippet);
    }

}
