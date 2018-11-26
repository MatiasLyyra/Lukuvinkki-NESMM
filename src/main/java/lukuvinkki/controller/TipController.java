package lukuvinkki.controller;

import lukuvinkki.domain.Tag;
import lukuvinkki.domain.Tip;
import lukuvinkki.repository.TagRepository;
import lukuvinkki.repository.TipRepository;
import lukuvinkki.util.TagParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TipController {
    @Autowired
    private TipRepository tipRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/addTip", method=RequestMethod.GET)
    public String tipForm(Model model){
        model.addAttribute("tip", new Tip());
        return "tipForm";
    }
    
    @RequestMapping(value = "/addTip", method = RequestMethod.POST)
    public String tipSubmit(@ModelAttribute Tip tip)
    {
        TagParser parser = new TagParser(tip.getRawTags());
        List<Tag> tags = getOrCreateTag(parser.parse());
        for(Tag tag : tags) {
            tip.addTag(tag);
        }
        tipRepository.save(tip);
        return "redirect:/";
   }

   @RequestMapping(value = "/tips", method = RequestMethod.GET)
   public String viewTips(Model model) {
        List<Tip> tips = tipRepository.findAllByOrderByCreatedDesc();
        model.addAttribute("tips", tips);
        return "tipList";
   }
   
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchTips(@RequestParam("keyword") String keyword, Model model) {
        
        if (keyword.isEmpty()) {
            List<Tip> allTips = tipRepository.findAllByOrderByCreatedDesc();
            model.addAttribute("tips", allTips);
            return "tipList";
        }
        
        // PRIMARY SEARCH BY TAGS OF TIPS
        List<Tag> tags = tagRepository.findByNameIgnoreCaseContaining(keyword);
        List<Tip> primaryTips = new ArrayList<>();
        for (Tag tag : tags) {
            tag.getTips().forEach(tip -> primaryTips.add(tip));
        }
        Collections.sort(primaryTips, (tip1, tip2) -> tip2.getCreated().compareTo(tip1.getCreated()));
        
        // SECONDARY SEARCH BY TITLES AND AUTHORS
        List<Tip> secondaryTips = tipRepository.findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrderByCreatedDesc(keyword, keyword);
        
        // MERGE TWO LISTS
        secondaryTips.removeAll(primaryTips);
        primaryTips.addAll(secondaryTips);
        
        model.addAttribute("tips", primaryTips);
        return "tipList";
    }

    private List<Tag> getOrCreateTag(List<String> rawTags) {
        List<Tag> tags = new ArrayList<>();
        for (String rawTag : rawTags) {
            Tag tag = tagRepository.findTagByName(rawTag);
            if (tag == null) {
                tag = new Tag(rawTag);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }
        return tags;
    }
}