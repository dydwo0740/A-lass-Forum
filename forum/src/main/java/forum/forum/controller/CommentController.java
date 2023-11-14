package forum.forum.controller;

import forum.forum.dto.CommentDto;
import forum.forum.entity.Comment;
import forum.forum.entity.Content;
import forum.forum.repository.CommentRepository;
import forum.forum.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;

    @PostMapping("/comments/new/{contentId}")
    public String saveComment(@ModelAttribute("comment") CommentDto commentDto, @PathVariable("contentId") Long id) {
        Content content = contentRepository.findById(id).get();
        Comment comment = Comment.builder()
                .writer(commentDto.getWriter())
                .content(commentDto.getContent())
                .build();
        content.addComment(comment);
        commentRepository.save(comment);
        return "redirect:/contents/" + String.valueOf(id);
    }

    @PostMapping("/comments/delete/{contentId}/{commentId}")
    public String deleteComment(@PathVariable("contentId") Long contentId, @PathVariable("commentId") Long commentId) {
        commentRepository.deleteById(commentId);
        return "redirect:/contents/" + String.valueOf(contentId);
    }

    @PostMapping("/comments/update/{contentId}/{commentId}")
    public String updateComment(@ModelAttribute("comment") CommentDto commentDto,
                                @PathVariable("contentId") Long contentId,
                                @PathVariable("commentId") Long commentId) {
        commentRepository.updateContentAndWriterById(commentId, commentDto.getContent(), commentDto.getWriter());

        // redirect:/contents/{contentId}로 수정
        return "redirect:/contents/" + contentId;
    }

}
