package be.nielsvermeiren.tag;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/technology")
public class TechnologyController {
    private final TechnologyRepository repository;

    @GetMapping
    Flux<Technology> findAll() {
        return repository.findAll();
    }

    @GetMapping(value ="/{name}")
    Mono<Technology> findByName(@PathVariable String name) {
        return repository.findById(name);
    }

    @PostMapping
    Mono<Technology> save(@RequestBody Technology technology) {
        return repository.save(technology);
    }
}
