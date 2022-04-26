package backend;

import java.util.ArrayList;
import java.util.Iterator;

public class Fleet {
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public void addVehicle(Vehicle newV) {
        Vehicle newVehicle = new Vehicle(newV.getRegistration(), newV.getType(), newV.getMaxLoad(), newV.getStatus());
        vehicles.add(newVehicle);
    }

    public void removeVehicle(Vehicle p) {
        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext()) {
            if (it.next().getRegistration() == p.getRegistration()) {
                it.remove();
            }
        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
