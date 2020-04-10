package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.repositories.SearchQueryRepository;
import pl.barwinscy.Akbarapp.repositories.SearchQueryRepositoryImpl;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.utils.SearchQueryCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        model.addAttribute("counties", searchService.getAllCounties());
        return "search";
    }

    @GetMapping("/search-result")
    public String getSearchResults(@ModelAttribute("form") SearchQueryCreator searchQueryCreator, Model model){
        String schoolQuery = searchQueryCreator.createQuery();
        List<School> searchedSchools = searchService.getSearchedSchools(schoolQuery);
        model.addAttribute("schools", searchedSchools);
        model.addAttribute("types", SchoolType.values());
        model.addAttribute("voivodeships", Voivodeship.values());
        model.addAttribute("counties", searchService.getAllCounties());
        return "search";
    }
}
