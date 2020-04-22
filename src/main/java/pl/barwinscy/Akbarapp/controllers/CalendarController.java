package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.utils.CalendarQueryCreator;
import pl.barwinscy.Akbarapp.utils.QueryCreator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {

    private SearchService searchService;

    public CalendarController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/calendar")
    public String getCalendarPage(Model model){
        model.addAttribute("form", new CalendarQueryCreator());
        model.addAttribute("counties", searchService.getAllCounties());
        return "calendar";
    }

    @GetMapping("/calendar-result")
    public String getCalendarResult(@ModelAttribute("form") CalendarQueryCreator calendarQueryCreator, Model model){
        String query = calendarQueryCreator.createCalendarQuery();
        Map<LocalDate, List<School>> searchedSchools = searchService.schoolMapByDate(query);
        model.addAttribute("schools", searchedSchools);
        model.addAttribute("counties", searchService.getAllCounties());
        return "calendar";
    }
}
