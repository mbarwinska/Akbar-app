package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.services.EmployeeService;
import pl.barwinscy.Akbarapp.services.SchoolService;
import pl.barwinscy.Akbarapp.services.SearchService;

@Controller
public class SchoolController {

    private SchoolService schoolService;
    private SearchService searchService;
    private EmployeeService employeeService;

    public SchoolController(SchoolService schoolService, SearchService searchService, EmployeeService employeeService) {
        this.schoolService = schoolService;
        this.searchService = searchService;
        this.employeeService = employeeService;
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
        model.addAttribute("counties", searchService.getAllCounties());
        model.addAttribute("employees", employeeService.getAllPhotographers());
        model.addAttribute("salesmen", employeeService.getAllSalesmen());

        return "school-form";
    }

    @GetMapping("/school/{schoolId}/update")
    public String updateSchoolForm(@PathVariable String schoolId, Model model) {
        SchoolDto schoolWithAllData = schoolService.getSchoolWithAllData(Long.valueOf(schoolId));
        model.addAttribute("newPhone", new PhoneDTO());
        model.addAttribute("school", schoolWithAllData);
        model.addAttribute("types", SchoolType.values());
        model.addAttribute("voivodeships", Voivodeship.values());
        model.addAttribute("counties", searchService.getAllCounties());
        model.addAttribute("employees", employeeService.getAllPhotographers());
        model.addAttribute("salesmen", employeeService.getAllSalesmen());
        return "school-update";
    }

    @PostMapping("/school")
    public String save(@ModelAttribute("school") SchoolDto schoolDto) {
        School saveSchool = schoolService.save(schoolDto);
        return "redirect:/school/" + saveSchool.getId();

    }

    @PostMapping("/update")
    public String update(@ModelAttribute("school") SchoolDto schoolDto,
                         @ModelAttribute("newPhone") PhoneDTO phoneDTO) {
        School saveSchool = schoolService.update(schoolDto, phoneDTO);

        return "redirect:/school/" + saveSchool.getId();
    }

    @GetMapping("/update/{schoolId}/delete-phone/{phoneId}")
    public String phoneDelete(@PathVariable Long phoneId, @PathVariable Long schoolId) {

        schoolService.deletePhone(phoneId);
        return "redirect:/school/" + schoolId + "/update";
    }

}
