package carwash;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class CarWashSystem {
    private final Map<String, Customer> customerMap = new HashMap<>();
    private final Map<String, Car> carMap = new HashMap<>();
    private final List<Appointment> appointments = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
    }   

    public void addCar(Car car) {
        carMap.put(car.getLicensePlate(), car);
    }   

    public Customer getCustomer(String customerId) {
        return customerMap.get(customerId);
    }

    public Car getCar(String licensePlate) {
        return carMap.get(licensePlate);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
