package pl.barwinscy.Akbarapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.barwinscy.Akbarapp.exceptions.SchoolNotFoundException;

@ControllerAdvice
public class GlobalHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SchoolNotFoundException.class)
    public String schoolNotFound(Exception e, Model model) {
        return getErrorView(e, model);
    }

    private String getErrorView(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error-page";
    }
}
