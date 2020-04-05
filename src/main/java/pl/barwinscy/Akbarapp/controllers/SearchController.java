package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.SearchService;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.utils.SearchQueryCreator;

import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String getSearchPage(Model model){
        model.addAttribute("form", new SearchQueryCreator());
        model.addAttribute("types", SchoolType.values());
        model.addAttribute("voivodeships", Voivodeship.values());
        return "search";
    }

    @PostMapping("/search")
    public String getSearchResults(SearchQueryCreator searchQueryCreator, Model model){
        String schoolQuery = searchQueryCreator.createQuery();
        List<School> searchedSchools = searchService.getSearchedSchools(schoolQuery);
        model.addAttribute("schools", searchedSchools);
        return "search";
    }
}
