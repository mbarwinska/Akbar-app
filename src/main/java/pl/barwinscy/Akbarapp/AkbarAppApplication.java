package pl.barwinscy.Akbarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.util.List;

@SpringBootApplication
public class AkbarAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkbarAppApplication.class, args);

		CsvReader csvReader = new CsvReader();
		List<SchoolDataCsv> allSchoolDataFromCsv = csvReader.getAllSchoolDataFromCsv();
		allSchoolDataFromCsv.forEach(System.out::println);
	}

}
