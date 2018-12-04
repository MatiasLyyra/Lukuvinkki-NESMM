package lukuvinkki.controller;

import lukuvinkki.domain.Comment;
import lukuvinkki.domain.Tip;
import lukuvinkki.repository.CommentRepository;
import lukuvinkki.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TipRepository tipRepository;

    @RequestMapping(value = "/comments/{tipId}/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @PathVariable long tipId) {
        Optional<Tip> tipOptional = tipRepository.findById(tipId);
        tipOptional.ifPresent(tip -> {
            tip.addComment(comment);
            tipRepository.save(tip);
        });
        return "redirect:/tips/" + tipId;
    }
}
