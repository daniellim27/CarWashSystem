package carwash;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PerformanceTest {
    public static void main(String[] args) {
        int n = 1000; // number of customers/cars/appointments to add
        int iterations = 10; // number of iterations

        long totalRuntime = 0;
        long totalMemoryUsed = 0;

        for (int i = 0; i < iterations; i++) {
            CarWashSystem system = new CarWashSystem();
            long startTime = System.nanoTime();
            long startMemory = getUsedMemory();

            for (int j = 0; j < n; j++) {
                // Generate customer details
                String customerId = "customer" + j;
                String customerName = "Customer Name" + j;
                String customerPhone = "1234567890";

                Customer customer = new Customer(customerId, customerName, customerPhone, new ArrayList<>());
                system.addCustomer(customer);

                // Generate car details
                String licensePlate = "ABC" + j;
                String carModel = "Model" + j;
                String carColor = "Color" + j;

                Car car = new Car(licensePlate, carModel, carColor);
                customer.getCars().add(car);
                system.addCar(car);

                // Generate appointment details
                LocalDateTime appointmentDateTime = LocalDateTime.now().plusDays(j % 30);
                WashPackage washPackage = WashPackage.BASIC;

                Appointment appointment = new Appointment(customer, car, washPackage, appointmentDateTime);
                system.addAppointment(appointment);
            }

            long endTime = System.nanoTime();
            long endMemory = getUsedMemory();

            totalRuntime += (endTime - startTime);
            totalMemoryUsed += (endMemory - startMemory);
        }

        long averageRuntime = totalRuntime / iterations;
        long averageMemoryUsed = totalMemoryUsed / iterations;

        System.out.println("Average runtime (nanoseconds): " + averageRuntime);
        System.out.println("Average memory used (bytes): " + averageMemoryUsed);
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Request garbage collection to get more accurate memory usage
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
