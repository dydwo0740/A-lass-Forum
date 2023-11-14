package forum.forum.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String writer;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content mainContent;

    @Builder
    public Comment(String writer, String content, Content mainContent) {
        this.writer = writer;
        this.content = content;
        this.mainContent = mainContent;
    }
}
