package pl.barwinscy.Akbarapp.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

public class CsvReader {

    //private static final String FILE_PATH = "src/main/resources/test.csv";

    @SuppressWarnings("unchecked")
    public List<SchoolDataCsv> getAllSchoolDataFromCsv(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            CsvToBean<SchoolDataCsv> csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(SchoolDataCsv.class)
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
