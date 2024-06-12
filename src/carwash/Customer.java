package carwash;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private List<Car> cars;

    public Customer(String id, String name, String phone, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cars = cars;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<Car> getCars() {
        return cars;
    }
}
