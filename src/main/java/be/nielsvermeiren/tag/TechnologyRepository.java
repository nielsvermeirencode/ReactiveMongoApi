package be.nielsvermeiren.tag;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends ReactiveCrudRepository<Technology, String> {
}
