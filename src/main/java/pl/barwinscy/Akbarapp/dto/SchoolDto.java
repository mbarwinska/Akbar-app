package pl.barwinscy.Akbarapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.barwinscy.Akbarapp.Voivodeship;

import java.time.LocalDate;

@Getter
@Setter
public class SchoolDto {

    private String name;
    private String type;
    private String street;
    private String zipCode;
    private String city;
    private String voivodeship;
    private String county;
    private String borough;
    private String phoneNumber;
    private String email;
    private String website;
    private String publicStatus;
    private boolean ours;
    private boolean contracted;
    private Integer calendarsLeftNumber;
    private String contactDate;
    private String photographingDate;
    private String payOffDate;
    private String employee;
    private String note1;
    private String note2;
    private String note3;

}
