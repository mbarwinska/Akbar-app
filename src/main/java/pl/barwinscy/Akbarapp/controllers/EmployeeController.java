package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.services.EmployeeService;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getEmployeesPage(Model model) {
        model.addAttribute("newEmployee", new EmployeeDto());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDto dto, Model model) {
        employeeService.addEmployee(dto);
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }
}
