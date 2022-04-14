// package PAF.Day12giphy.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import PAF.Day12giphy.service.GifService;

// @Controller
// @RequestMapping(path="/")
// public class GifController {

//     @Autowired
//     private GifService gifSvc;
    
//     @GetMapping(path="/search")
//     public String getGif(@RequestParam String q, @RequestParam Integer limit, @RequestParam String rating, Model model) {
//         System.out.printf(">>> q = %s, limit = %d, rating = %s\n", q, limit, rating);
        
//         List<String> images = gifSvc.createGiphy(q, limit, rating);
//         model.addAttribute("q", q);
//         model.addAttribute("images", images);
//         return "search";
//     }
// }
