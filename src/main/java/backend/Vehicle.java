package backend;

public class Vehicle {
    private String type;
    private String registration;
    private String maxLoad;
    private String status;


    public Vehicle(String registration, String type, String maxLoad, String status) {
        this.registration = registration;
        this.type = type;
        this.maxLoad = maxLoad;
        this.status = status;
    }

    public Vehicle(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(String maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
