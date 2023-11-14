package forum.forum.controller;

import forum.forum.dto.CommentDto;
import forum.forum.dto.ContentDto;
import forum.forum.entity.Comment;
import forum.forum.entity.Content;
import forum.forum.repository.CommentRepository;
import forum.forum.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/contents/new")
    public String createForm(Model model) {
        model.addAttribute("content", new ContentDto());
        return "/operation/write";
    }

    @PostMapping("/contents/new")
    public String save(@ModelAttribute("content") ContentDto contentDto) {
        Content content = Content.builder()
                .content(contentDto.getContent())
                .title(contentDto.getTitle())
                .writer(contentDto.getWriter())
                .build();
        contentRepository.save(content);
        return "redirect:/";
    }

    @GetMapping("/contents/{contentId}")
    public String contentInfo(Model model, @PathVariable("contentId") Long id) {
        Content content = contentRepository.findById(id).get();
        model.addAttribute("content", content);
        List<Comment> comments = commentRepository.findByMainContentId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new CommentDto());
        return "view/content";
    }
}
