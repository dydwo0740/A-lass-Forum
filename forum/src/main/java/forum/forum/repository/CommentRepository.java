package forum.forum.repository;

import forum.forum.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByMainContentId(Long contentId);
    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.content = :newContent, c.writer = :newWriter WHERE c.id = :id")
    void updateContentAndWriterById(@Param("id") Long id, @Param("newContent") String newContent, @Param("newWriter") String newWriter);

}
