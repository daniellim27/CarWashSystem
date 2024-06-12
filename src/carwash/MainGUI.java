package carwash;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainGUI extends JFrame {
    private final CarWashSystem system;
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField customerPhoneField;
    private JTextField carLicenseField;
    private JTextField carModelField;
    private JTextField carColorField;
    private JSpinner appointmentDateSpinner;
    private JSpinner appointmentTimeSpinner;
    private JComboBox<WashPackage> washPackageComboBox;
    private JTable appointmentsTable;
    private DefaultTableModel tableModel;
    private JLabel statusBar;

    public MainGUI() {
        system = new CarWashSystem();
        setTitle("Car Wash Management System");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(32, 32, 32));  // Dark gray background
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel titleLabel = new JLabel("Car Wash Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 255, 255));  // Light blue
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Input fields and labels
        gbc.gridwidth = 1;
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Customer ID:", customerIdField = new JTextField());
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Customer Name:", customerNameField = new JTextField());
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Customer Phone:", customerPhoneField = new JTextField());
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Car License Plate:", carLicenseField = new JTextField());
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Car Model:", carModelField = new JTextField());
        gbc.gridy++;
        addLabelAndField(panel, gbc, "Car Color:", carColorField = new JTextField());

        // Appointment date picker
        gbc.gridy++;
        JLabel dateLabel = new JLabel("Appointment Date:");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(new Color(255, 255, 255));  // Light blue
        gbc.gridx = 0;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        appointmentDateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(appointmentDateSpinner, "yyyy-MM-dd");
        appointmentDateSpinner.setEditor(dateEditor);
        panel.add(appointmentDateSpinner, gbc);

        // Appointment time picker
        gbc.gridy++;
        JLabel timeLabel = new JLabel("Appointment Time:");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timeLabel.setForeground(new Color(255, 255, 255));  // Light blue
        gbc.gridx = 0;
        panel.add(timeLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel timeModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        appointmentTimeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(appointmentTimeSpinner, "HH:mm");
        appointmentTimeSpinner.setEditor(timeEditor);
        panel.add(appointmentTimeSpinner, gbc);

        // Wash package combo box
        gbc.gridy++;
        JLabel washPackageLabel = new JLabel("Wash Package:");
        washPackageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        washPackageLabel.setForeground(new Color(255, 255, 255));  // Light blue
        washPackageComboBox = new JComboBox<>(WashPackage.values());
        washPackageComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        washPackageComboBox.setBackground(new Color(255, 255, 255));  // Light blue
        washPackageComboBox.setForeground(Color.BLACK);
        gbc.gridx = 0;
        panel.add(washPackageLabel, gbc);
        gbc.gridx = 1;
        panel.add(washPackageComboBox, gbc);

        // Submit and Reset buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(109, 202, 27));  // Dark red
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(new SubmitButtonListener());
        panel.add(submitButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(139, 0, 0));  // Dark red
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(new ResetButtonListener());
        gbc.gridx = 1;
        panel.add(resetButton, gbc);

        // Appointments table
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        tableModel = new DefaultTableModel(new String[]{"Customer Name", "Car Model", "Appointment Time", "Wash Package"}, 0);
        appointmentsTable = new JTable(tableModel);
        JScrollPane
                scrollPane = new JScrollPane(appointmentsTable);
        scrollPane.setPreferredSize(new Dimension(850, 200));
        panel.add(scrollPane, gbc);

        // Remove button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton removeButton = new JButton("Remove Selected Booking");
        removeButton.setFont(new Font("Arial", Font.BOLD, 16));
        removeButton.setBackground(new Color(139, 0, 0));  // Dark red
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(new RemoveButtonListener());
        panel.add(removeButton, gbc);

        // Status bar
        gbc.gridy++;
        statusBar = new JLabel(" ");
        statusBar.setFont(new Font("Arial", Font.PLAIN, 14));
        statusBar.setForeground(Color.WHITE);  // White
        panel.add(statusBar, gbc);

        add(panel);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(255, 255, 255));  // Light blue
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(textField, gbc);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String customerId = customerIdField.getText();
                String customerName = customerNameField.getText();
                String customerPhone = customerPhoneField.getText();
                String carLicense = carLicenseField.getText();
                String carModel = carModelField.getText();
                String carColor = carColorField.getText();
                Date selectedDate = (Date) appointmentDateSpinner.getValue();
                Date selectedTime = (Date) appointmentTimeSpinner.getValue();
                WashPackage washPackage = (WashPackage) washPackageComboBox.getSelectedItem();

                if (customerId.isEmpty() || customerName.isEmpty() || customerPhone.isEmpty() ||
                        carLicense.isEmpty() || carModel.isEmpty() || carColor.isEmpty() || selectedDate == null) {
                    statusBar.setText("Error: All fields must be filled out.");
                    return;
                }

                LocalDateTime appointmentDateTime = LocalDateTime.of(
                        selectedDate.getYear() + 1900,
                        selectedDate.getMonth() + 1,
                        selectedDate.getDate(),
                        selectedTime.getHours(),
                        selectedTime.getMinutes()
                );

                Customer customer = new Customer(customerId, customerName, customerPhone, new ArrayList<>());
                system.addCustomer(customer);

                Car car = new Car(carLicense, carModel, carColor);
                customer.getCars().add(car);
                system.addCar(car);

                Appointment appointment = new Appointment(customer, car, washPackage, appointmentDateTime);
                system.addAppointment(appointment);

                // Update table
                tableModel.addRow(new Object[]{customer.getName(), car.getModel(), appointment.getAppointmentTime(), appointment.getWashPackage()});

                statusBar.setText("Appointment successfully added!");

                // Clear fields after submission
                resetFields();

            } catch (Exception ex) {
                statusBar.setText("Error: " + ex.getMessage());
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetFields();
        }
    }

    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = appointmentsTable.getSelectedRow();
            if (selectedRow >= 0) {
                // Remove from system
                Appointment appointmentToRemove = system.getAppointments().get(selectedRow);
                system.getAppointments().remove(appointmentToRemove);

                // Remove from table
                tableModel.removeRow(selectedRow);

                statusBar.setText("Appointment successfully removed.");
            } else {
                statusBar.setText("Error: No booking selected.");
            }
        }
    }

    private void resetFields() {
        customerIdField.setText("");
        customerNameField.setText("");
        customerPhoneField.setText("");
        carLicenseField.setText("");
        carModelField.setText("");
        carColorField.setText("");
        appointmentDateSpinner.setValue(new Date());
        appointmentTimeSpinner.setValue(new Date());
        washPackageComboBox.setSelectedIndex(0);
        statusBar.setText(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}

