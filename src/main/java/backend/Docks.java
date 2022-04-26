package backend;

import com.opencsv.bean.CsvBindByName;
import org.springframework.context.annotation.Bean;

public class Docks {
    @CsvBindByName(column="Muelle", required = true)
    private String id;
    @CsvBindByName(column="Tipo", required = true)
    private String type;
    @CsvBindByName(column="6:00-7:00", required = true)
    private String hour1;
    @CsvBindByName(column="7:00-8:00", required = true)
    private String hour2;
    @CsvBindByName(column="8:00-9:00", required = true)
    private String hour3;
    @CsvBindByName(column="9:00-10:00", required = true)
    private String hour4;
    @CsvBindByName(column="10:00-11:00", required = true)
    private String hour5;
    @CsvBindByName(column="11:00-12:00", required = true)
    private String hour6;
    @CsvBindByName(column="12:00-13:00", required = true)
    private String hour7;
    @CsvBindByName(column="13:00-14:00", required = true)
    private String hour8;

    public Docks() {
    }

    public Docks(String id, String type, String hour1, String hour2, String hour3, String hour4, String hour5, String hour6, String hour7, String hour8) {
        this.id = id;
        this.type = type;
        this.hour1 = hour1;
        this.hour2 = hour2;
        this.hour3 = hour3;
        this.hour4 = hour4;
        this.hour5 = hour5;
        this.hour6 = hour6;
        this.hour7 = hour7;
        this.hour8 = hour8;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHour1() {
        return hour1;
    }

    public void setHour1(String hour1) {
        this.hour1 = hour1;
    }

    public String getHour2() {
        return hour2;
    }

    public void setHour2(String hour2) {
        this.hour2 = hour2;
    }

    public String getHour3() {
        return hour3;
    }

    public void setHour3(String hour3) {
        this.hour3 = hour3;
    }

    public String getHour4() {
        return hour4;
    }

    public void setHour4(String hour4) {
        this.hour4 = hour4;
    }

    public String getHour5() {
        return hour5;
    }

    public void setHour5(String hour5) {
        this.hour5 = hour5;
    }

    public String getHour6() {
        return hour6;
    }

    public void setHour6(String hour6) {
        this.hour6 = hour6;
    }

    public String getHour7() {
        return hour7;
    }

    public void setHour7(String hour7) {
        this.hour7 = hour7;
    }

    public String getHour8() {
        return hour8;
    }

    public void setHour8(String hour8) {
        this.hour8 = hour8;
    }

    @Override
    public String toString() {
        return "Docks{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", hour1='" + hour1 + '\'' +
                ", hour2='" + hour2 + '\'' +
                ", hour3='" + hour3 + '\'' +
                ", hour4='" + hour4 + '\'' +
                ", hour5='" + hour5 + '\'' +
                ", hour6='" + hour6 + '\'' +
                ", hour7='" + hour7 + '\'' +
                ", hour8='" + hour8 + '\'' +
                '}';
    }
}
