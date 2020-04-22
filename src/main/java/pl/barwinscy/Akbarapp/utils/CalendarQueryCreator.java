package pl.barwinscy.Akbarapp.utils;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CalendarQueryCreator {

    private String county;
    private String borough;
    private String dateType;
    private String dateFrom;
    private String dateTo;


    public String createCalendarQuery(){

        StringBuilder query = new StringBuilder("SELECT school FROM School as school JOIN Schedule as schedule on school.id = schedule.school.id WHERE ");

        if (dateFrom.isEmpty()){
            LocalDate nowDate = LocalDate.now();
            query.append("schedule.").append(dateType).append(" BETWEEN '").append(nowDate.toString()).append("' AND '").append(nowDate.plusDays(30L).toString()).append("' AND");
        }
        else if(dateTo.isEmpty()){
            LocalDate date = LocalDate.parse(dateFrom);
            query.append("schedule.").append(dateType).append(" BETWEEN '").append(date.toString()).append("' AND '").append(date.plusDays(30L).toString()).append("' AND");
        }
        else{
            LocalDate dateF = LocalDate.parse(dateFrom);
            LocalDate dateT = LocalDate.parse(dateTo);
            query.append("schedule.").append(dateType).append(" BETWEEN '").append(dateF.toString()).append("' AND '").append(dateT.toString()).append("' AND");
        }

        if (!county.isEmpty()) {
            query.append(" school.address.county like '%").append(county).append("%' AND");
        }
        if (!borough.isEmpty()) {
            query.append(" school.address.borough like '").append(borough).append("%' AND");
        }

        query.delete(query.length() - 4, query.length());
        return query.toString();
    }
}
