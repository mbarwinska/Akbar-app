package pl.barwinscy.Akbarapp.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchQueryCreator {

    private String voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String type;
    private String name;
    private String phone;

    public SearchQueryCreator(){

    }

    public SearchQueryCreator(String voivodeship, String county, String borough, String city, String street, String type, String name, String phone) {
        this.voivodeship = voivodeship;
        this.county = county;
        this.borough = borough;
        this.city = city;
        this.street = street;
        this.type = type;
        this.name = name;
        this.phone = phone;
    }

    public String  createQuery() {
        String query = "SELECT school FROM School as school";
        if (!phone.isEmpty()) {
            query += " JOIN Phone as phone on school.id = phone.schoolRSPO";
        }
        query += " WHERE";
        if (!phone.isEmpty()) {
            query += " school.id = (select schoolRSPO FROM Phone WHERE phone_number = " + phone + ")" + " AND";
        }
        if (!voivodeship.isEmpty()) {
            query += " school.address.voivodeship = '" + voivodeship + "' AND";
        }
        if (!county.isEmpty()) {
            query += " school.address.county like '%" + county + "%' AND";
        }
        if (!borough.isEmpty()) {
            query += " school.address.borough like '" + borough + "%' AND";
        }
        if (!city.isEmpty()) {
            query += " school.address.city like '" + city + "%' AND";
        }
        if (!street.isEmpty()) {
            query += " school.address.street like '%" + street + "%' AND";
        }
        if (!type.isEmpty()) {
            query += " school.type = '" + type + "' AND";
        }
        if (!name.isEmpty()) {
            query += " school.name like '%" + name + "%' AND";
        }
        query = query.substring(0, query.length() - 4);
        return query;

    }

}

