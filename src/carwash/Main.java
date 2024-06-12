package carwash;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarWashSystem system = new CarWashSystem();
        Scanner scanner = new Scanner(System.in);

        // Input customer details
        System.out.println("Enter customer ID: ");
        String customerId = scanner.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer phone: ");
        String customerPhone = scanner.nextLine();

        Customer customer = new Customer(customerId, customerName, customerPhone, new ArrayList<>());
        system.addCustomer(customer);

        // Input car details
        System.out.println("Enter car license plate: ");
        String licensePlate = scanner.nextLine();

        System.out.println("Enter car model: ");
        String carModel = scanner.nextLine();

        System.out.println("Enter car color: ");
        String carColor = scanner.nextLine();

        Car car = new Car(licensePlate, carModel, carColor);
        customer.getCars().add(car);
        system.addCar(car);

        // Input appointment details
        System.out.println("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
        String appointmentDateTimeStr = scanner.nextLine();
        LocalDateTime appointmentDateTime = LocalDateTime.parse(appointmentDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("Enter wash package (BASIC, DELUXE, PREMIUM): ");
        String washPackageStr = scanner.nextLine();
        WashPackage washPackage = WashPackage.valueOf(washPackageStr.toUpperCase());

        Appointment appointment = new Appointment(customer, car, washPackage, appointmentDateTime);
        system.addAppointment(appointment);

        // Print entered details
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Car Model: " + car.getModel());
        System.out.println("Appointment Time: " + appointment.getAppointmentTime());
        System.out.println("Wash Package: " + appointment.getWashPackage());

        scanner.close();
    }
}
