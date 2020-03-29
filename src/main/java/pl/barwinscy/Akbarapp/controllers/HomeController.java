package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {


    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @PostMapping("/search")
    public String getSearchPage(){
        return "search";
    }

}
