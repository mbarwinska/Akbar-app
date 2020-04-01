package pl.barwinscy.Akbarapp.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barwinscy.Akbarapp.Voivodeship;

import javax.persistence.Embeddable;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Embeddable
public class Address {

    private Voivodeship voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String zipCode;

    protected Address() {

    }

    public Address(Voivodeship voivodeship, String county, String borough, String city, String street, String zipCode) {
        this.voivodeship = voivodeship;
        this.county = county;
        this.borough = borough;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
