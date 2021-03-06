package pl.barwinscy.Akbarapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
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
        model.addAttribute("newSalesman", new SalesmanDto());
        addEmployeesAndSalesmenToSelectLists(model);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployee(@ModelAttribute("newEmployee") EmployeeDto dto, Model model) {
        employeeService.addEmployee(dto);
        model.addAttribute("newSalesman", new SalesmanDto());
        addEmployeesAndSalesmenToSelectLists(model);
        return "employees";
    }

    @GetMapping("/salesmen/add")
    public String addSalesman(@ModelAttribute("newSalesman") SalesmanDto dto, Model model) {
        employeeService.addSalesman(dto);
        model.addAttribute("newEmployee", new EmployeeDto());
        addEmployeesAndSalesmenToSelectLists(model);
        return "employees";
    }

    private void addEmployeesAndSalesmenToSelectLists(Model model) {
        model.addAttribute("employees", employeeService.getAllPhotographers());
        model.addAttribute("salesmen", employeeService.getAllSalesmen());
    }
}
