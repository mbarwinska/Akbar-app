package pl.barwinscy.Akbarapp.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryCreator {

    private String voivodeship;
    private String county;
    private String borough;
    private String city;
    private String street;
    private String type;
    private String name;
    private String phone;
    private boolean ours;
    private boolean contracted;
    private boolean calendarsLeft;
    private boolean noCalendars;
    private boolean noContract;
    private boolean notOurs;

    public QueryCreator() {

    }

    public QueryCreator(String voivodeship, String county, String borough, String city, String street, String type, String name, String phone) {
        this.voivodeship = voivodeship;
        this.county = county;
        this.borough = borough;
        this.city = city;
        this.street = street;
        this.type = type;
        this.name = name;
        this.phone = phone;
    }

    public String createSearchQuery() {
        String query = "SELECT DISTINCT school FROM School as school";
        if (!phone.isEmpty()) {
            query += " JOIN Phone as phone on school.id = phone.school.id";
        }
        query += " WHERE";
        if (!phone.isEmpty()) {
            query += " school.id = (SELECT phone.school.id FROM Phone as phone WHERE phone.phoneNumber = '" + phone + "')" + " AND";
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

    public String createFilterQuery() {

        String query = "SELECT school FROM School as school";
        if (ours || contracted || calendarsLeft || noCalendars || noContract || notOurs){
            query += " LEFT JOIN Status as status ON school.id = status.school.id";
            query = insertStatusToQuery(query);
        }
        else {
            query += " WHERE";
        }
        if (!voivodeship.isEmpty()) {
            query += " school.address.voivodeship = '" + voivodeship + "' AND";
        }
        if (county != null) {
            query += " school.address.county IN ('";
            String[] counties = county.split(",");
            if (counties.length > 1) {
                query = insertParamToQuery(counties, query);
            } else {
                query += county + "') AND";
            }
        }
        if (!borough.isEmpty()) {
            query += " school.address.borough like '" + borough + "%' AND";
        }
        if (!city.isEmpty()) {
            query += " school.address.city like '" + city + "%' AND";
        }

        if (type != null) {
            query += " school.type IN ('";
            String[] types = type.split(",");
            if (types.length > 1){
                query = insertParamToQuery(types,query);
            } else{
                query += type + "') AND";
            }

        }
        query = query.substring(0, query.length() - 4);
        return query;
    }

    private String insertParamToQuery(String[] queryParams, String query) {
        StringBuilder queryBuilder = new StringBuilder(query);
        for (String queryParam : queryParams) {
            queryBuilder.append(queryParam).append("', '");
        }
        query = queryBuilder.toString();
        query = query.substring(0, query.length() - 3);
        query += ") AND";
        return query;
    }

    private String insertStatusToQuery(String query){
        query += " WHERE";
        if (ours){
            query += " status.ours = 1 AND";
        }
        if (contracted){
            query += " status.contracted = 1 AND";
        }
        if (calendarsLeft){
            query += " status.calendarsLeftNumber > 0 AND";
        }
        if(noCalendars){
            query += " (status.calendarsLeftNumber IS NULL OR status.calendarsLeftNumber = 0) AND";
        } if(noContract){
            query += " (status.contracted IS NULL OR status.contracted = 0) AND";
        }
        if(notOurs){
            query += " (status.ours = 0 OR status.ours IS NULL) AND";
        }
        query = query.substring(0,query.length() - 4);
        query += " AND";
        return query;
    }
}

