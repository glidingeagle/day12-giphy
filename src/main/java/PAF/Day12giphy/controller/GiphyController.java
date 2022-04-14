package PAF.Day12giphy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PAF.Day12giphy.service.GiphyService;

@Controller
@RequestMapping (path ="/")
public class GiphyController {

    @Autowired
    private GiphyService giphySvc;
    
    @GetMapping (path = "/search")
    public String getGiphy (@RequestParam String q, @RequestParam Integer limit, @RequestParam String rating, Model model) {
        
        System.out.printf(">>>>> q = %s, limit = %d, rating = %s\n", q, limit, rating);
        List<String> result = giphySvc.getGiphs(q, rating, limit);
        model.addAttribute("q", q);
        model.addAttribute("result", result);
        return "search-result";
    }
}
