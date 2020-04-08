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

    public String createQuery() {
        StringBuilder query = new StringBuilder("SELECT school FROM School as school");

        if (!phone.isEmpty()) {
            query.append(" JOIN Phone as phone on school.id = phone.schoolRSPO");
        }
        query.append(" WHERE");

        if (!phone.isEmpty()) {
            query.append(" school.id = (select schoolRSPO FROM Phone WHERE phone_number = ").append(phone).append(") AND");
        }
        if (!voivodeship.isEmpty()) {
            query.append(" school.address.voivodeship = ").append(voivodeship).append(" AND");
        }
        if (!county.isEmpty()) {
            query.append(" school.address.county like '%").append(county).append("%' AND");
        }
        if (!borough.isEmpty()) {
            query.append(" school.address.borough like '").append(borough).append("%' AND");
        }
        if (!city.isEmpty()) {
            query.append(" school.address.city like '").append(city).append("%' AND");
        }
        if (!street.isEmpty()) {
            query.append(" school.address.street like '%").append(street).append("%' AND");
        }
        if (!type.isEmpty()) {
            query.append(" school.type = ").append(type).append(" AND");
        }
        if (!name.isEmpty()) {
            query.append(" school.name like '%").append(name).append("%' AND");
        }

        @SuppressWarnings(value = "uncheck")
        String readyQuery = query.replace(query.length() - 4, query.length(), "").toString();
        return readyQuery;
    }

}

