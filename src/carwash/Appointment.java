    package carwash;

    import java.time.LocalDateTime;

    public class Appointment {
        private Customer customer;
        private Car car;
        private WashPackage washPackage;
        private LocalDateTime appointmentTime;

        public Appointment(Customer customer, Car car, WashPackage washPackage, LocalDateTime appointmentTime) {
            this.customer = customer;
            this.car = car;
            this.washPackage = washPackage;
            this.appointmentTime = appointmentTime;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Car getCar() {
            return car;
        }

        public WashPackage getWashPackage() {
            return washPackage;
        }

        public LocalDateTime getAppointmentTime() {
            return appointmentTime;
        }
    }
