package pl.barwinscy.Akbarapp.utils;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SchoolDataCsv {

    @CsvBindByName(column = "Typ")
    private String type;

    @CsvBindByName(column = "Nazwa")
    private String name;

    @CsvBindByName(column = "Ulica")
    private String street;

    @CsvBindByName(column = "Numer budynku")
    private String buildingNumber;

    @CsvBindByName(column = "Numer lokalu")
    private String localNumber;

    @CsvBindByName(column = "Kod pocztowy")
    private String zipCode;

    @CsvBindByName(column = "Miejscowość")
    private String city;

    @CsvBindByName(column = "Telefon")
    private String phone;

    @CsvBindByName(column = "E-mail")
    private String email;

    @CsvBindByName(column = "Strona www")
    private String website;

    @CsvBindByName(column = "Województwo")
    private String voivodeship;

    @CsvBindByName(column = "Powiat")
    private String county;

    @CsvBindByName(column = "Gmina")
    private String borough;

    @CsvBindByName(column = "Publiczność status")
    private String status;
}
