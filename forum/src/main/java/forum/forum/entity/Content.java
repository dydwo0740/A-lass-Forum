package forum.forum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;
    private String writer;
    private String title;
    private String content;

    private LocalDateTime saveDate; // 작성한 시간 저장하고 싶다

    @OneToMany(mappedBy = "mainContent")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setMainContent(this);
    }

    @Builder
    public Content(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
