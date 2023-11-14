package forum.forum.controller;

import forum.forum.entity.Content;
import forum.forum.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContentRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        List<Content> contents = repository.findAll();
        model.addAttribute("contents", contents);
        return "home";
    }

}
