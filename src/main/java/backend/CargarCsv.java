package backend;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargarCsv {

    public static List<Docks> load(){
        try {
            List<Docks> docks = new CsvToBeanBuilder(new FileReader("configuracion_muelles.csv")).withType(Docks.class).withSeparator(';').build().parse();

            for(Docks muelle: docks){
                System.out.println(muelle.toString());
            }
            return docks;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
