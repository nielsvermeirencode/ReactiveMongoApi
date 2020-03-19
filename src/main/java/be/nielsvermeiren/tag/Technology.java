package be.nielsvermeiren.tag;

import be.nielsvermeiren.snippet.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    @Id
    private String name;
    private List<Snippet> snippets;
}
