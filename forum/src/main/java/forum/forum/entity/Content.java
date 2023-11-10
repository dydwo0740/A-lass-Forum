package forum.forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Content {
    @Id @GeneratedValue
    private Long id;
    private String writer;
    private String title;
    private String content;

    private LocalDateTime saveDate;

    @Builder
    public Content(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
