package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.utils.QueryCreator;

import java.util.List;

@Controller
public class FiltersController {

    private SearchService searchService;

    public FiltersController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/filters")
    public String getFiltersPage(Model model) {
        model.addAttribute("form", new QueryCreator());
        model.addAttribute("voivodeships", Voivodeship.values());
        model.addAttribute("counties", searchService.getAllCounties());
        model.addAttribute("types", SchoolType.values());
        return "filters";
    }

    @GetMapping("/filters-result")
    public String getFiltersResult(@ModelAttribute("form") QueryCreator queryCreator, Model model) {
        String query = queryCreator.createFilterQuery();
        List<School> searchedSchools = searchService.getSearchedSchools(query);
        model.addAttribute("schools", searchedSchools);
        model.addAttribute("types", SchoolType.values());
        model.addAttribute("voivodeships", Voivodeship.values());
        model.addAttribute("counties", searchService.getAllCounties());
        return "filters";
    }
}
