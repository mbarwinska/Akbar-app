package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.barwinscy.Akbarapp.services.SchoolService;

@Controller
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/search/{schoolId}")
    public String getSchoolViewPage(@PathVariable String schoolId, Model model){
        model.addAttribute("school",schoolService.getSchoolWithAllData(Long.valueOf(schoolId)));
        return "school-view";
    }
}
