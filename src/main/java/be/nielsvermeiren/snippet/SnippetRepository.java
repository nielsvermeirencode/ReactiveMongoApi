package be.nielsvermeiren.snippet;

import be.nielsvermeiren.tag.Technology;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SnippetRepository extends ReactiveCrudRepository<Snippet, String> {
    Flux<Snippet> findAllByLanguage(Language lang);
    Flux<Snippet> findAllByTechnologies(Technology technology);
}
