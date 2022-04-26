package backend;

import java.util.Date;

public class Booking {

    private String matricula;
    private String muelle;
    private String rango;
    private String driver;
    private Boolean  dangerGoods;

    public Booking() {
    }

    public Booking(String matricula, String muelle, String rango, String driver, Boolean dangerGoods) {
        this.matricula = matricula;
        this.muelle = muelle;
        this.rango = rango;
        this.driver = driver;
        this.dangerGoods = dangerGoods;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMuelle() {
        return muelle;
    }

    public void setMuelle(String muelle) {
        this.muelle = muelle;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Boolean getDangerGoods() {
        return dangerGoods;
    }

    public void setDangerGoods(Boolean dangerGoods) {
        this.dangerGoods = dangerGoods;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "matricula='" + matricula + '\'' +
                ", muelle='" + muelle + '\'' +
                ", rango='" + rango + '\'' +
                ", driver='" + driver + '\'' +
                ", dangerGoods=" + dangerGoods +
                '}';
    }
}
