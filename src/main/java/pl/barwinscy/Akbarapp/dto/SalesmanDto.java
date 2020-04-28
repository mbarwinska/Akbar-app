package pl.barwinscy.Akbarapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class SalesmanDto {
    @NotBlank(message = "Name cannot be blank")
    private String firstName;
    private String lastName;
}
