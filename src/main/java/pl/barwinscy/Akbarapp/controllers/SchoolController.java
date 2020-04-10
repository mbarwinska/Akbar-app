package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.services.SchoolService;
import pl.barwinscy.Akbarapp.services.SearchService;

@Controller
public class SchoolController {

    private SchoolService schoolService;
    private SearchService searchService;

    public SchoolController(SchoolService schoolService, SearchService searchService) {
        this.schoolService = schoolService;
        this.searchService = searchService;
    }

    @GetMapping("/school/{schoolId}")
    public String getSchoolViewPage(@PathVariable String schoolId, Model model) {
        model.addAttribute("school", schoolService.getSchoolWithAllData(Long.valueOf(schoolId)));
        return "school-view";
    }

    @GetMapping("/school/new")
    public String newSchoolForm(Model model) {
        model.addAttribute("school", new SchoolDto());
        model.addAttribute("types", SchoolType.values());
        model.addAttribute("voivodeships", Voivodeship.values());
        model.addAttribute("counties",searchService.getAllCounties());
        return "school-form";
    }

    @PostMapping("/school")
    public String saveOrUpdate(@ModelAttribute("school") SchoolDto schoolDto) {
        School saveSchool = schoolService.save(schoolDto);
        return "redirect:/school/" + saveSchool.getId();

    }

}
