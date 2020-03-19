package be.nielsvermeiren;

import be.nielsvermeiren.snippet.SnippetRepository;
import be.nielsvermeiren.tag.TechnologyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {TechnologyRepository.class, SnippetRepository.class})
public class ReactiveSpringMongoApiDemoApplication  {
	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringMongoApiDemoApplication.class, args);
	}
}
