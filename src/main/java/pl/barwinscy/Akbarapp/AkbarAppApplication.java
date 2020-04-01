package pl.barwinscy.Akbarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;
import pl.barwinscy.Akbarapp.utils.SearchQueryCreator;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class AkbarAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(AkbarAppApplication.class, args);


		CsvReader csvReader = new CsvReader();
		List<SchoolDataCsv> allSchoolDataFromCsv = csvReader.getAllSchoolDataFromCsv();

		List<School> schoolsToSave = allSchoolDataFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());


	}
}
