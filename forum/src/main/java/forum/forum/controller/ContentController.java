package forum.forum.controller;

import forum.forum.dto.ContentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {
    @GetMapping("/contents/new")
    public String createForm(Model model){
        model.addAttribute("content", new ContentDto());
        return "";
    }
    @PostMapping("/contents/new")
    public String save() {
        return "redirect:/home";
    }
}
