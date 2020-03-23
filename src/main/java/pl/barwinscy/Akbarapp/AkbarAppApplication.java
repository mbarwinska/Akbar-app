package pl.barwinscy.Akbarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AkbarAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkbarAppApplication.class, args);

		CsvReader csvReader = new CsvReader();
		List<SchoolDataCsv> allSchoolDataFromCsv = csvReader.getAllSchoolDataFromCsv();
		//allSchoolDataFromCsv.forEach(System.out::println);
		List<School> schoolList =new ArrayList<>();

		for (int i = 0; i < allSchoolDataFromCsv.size(); i++){
			schoolList.add(EntityMapper.mapToSchoolEntity(allSchoolDataFromCsv.get(i)));
		}

		schoolList.forEach(System.out::println);
	}

}
