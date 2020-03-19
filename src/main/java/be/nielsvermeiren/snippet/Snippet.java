package be.nielsvermeiren.snippet;

import be.nielsvermeiren.config.CascadeSave;
import be.nielsvermeiren.tag.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {
    @Id
    private String name;
    private Language language;
    @CascadeSave
    private List<Technology> technologies;
    private String explanation;
    private String snippet;
}
