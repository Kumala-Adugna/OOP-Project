package gui;

import employee.*;
import services.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class EmployeeManagementGUI extends JFrame {

    private final EmployeeService service = new EmployeeService();

    // Fields
    private final JTextField txtId = new JTextField(10);
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtDept = new JTextField(15);
    private final JTextField txtCity = new JTextField(10);
    private final JTextField txtSubCity = new JTextField(10);
    private final JTextField txtCountry = new JTextField(10);

    private final JComboBox<String> cmbType =
            new JComboBox<>(new String[]{"Full-Time", "Part-Time", "Intern"});

    private final JTextField txtMonthlySalary = new JTextField(10);
    private final JTextField txtHourlyRate = new JTextField(10);
    private final JTextField txtHoursWorked = new JTextField(10);
    private final JTextField txtStipend = new JTextField(10);

    private final JTextArea output = new JTextArea(12, 45);
    private final JTextField txtSearchId = new JTextField(10);

    public EmployeeManagementGUI() {
        super("Employee Management System");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(buildForm(), BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        add(buildActions(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setTypeFieldVisibility();
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);

        int r = 0;

        c.gridy = r;
        c.gridx = 0; p.add(new JLabel("ID:"), c);
        c.gridx = 1; p.add(txtId, c);

        c.gridx = 2; p.add(new JLabel("Name:"), c);
        c.gridx = 3; p.add(txtName, c);

        c.gridx = 4; p.add(new JLabel("Type:"), c);
        c.gridx = 5; p.add(cmbType, c);

        r++;
        c.gridy = r;
        c.gridx = 0; p.add(new JLabel("Department:"), c);
        c.gridx = 1; p.add(txtDept, c);

        r++;
        c.gridy = r;
        c.gridx = 0; p.add(new JLabel("City:"), c);
        c.gridx = 1; p.add(txtCity, c);

        c.gridx = 2; p.add(new JLabel("SubCity:"), c);
        c.gridx = 3; p.add(txtSubCity, c);

        c.gridx = 4; p.add(new JLabel("Country:"), c);
        c.gridx = 5; p.add(txtCountry, c);

        r++;
        c.gridy = r;
        c.gridx = 0; p.add(new JLabel("Monthly:"), c);
        c.gridx = 1; p.add(txtMonthlySalary, c);

        c.gridx = 2; p.add(new JLabel("Hourly:"), c);
        c.gridx = 3; p.add(txtHourlyRate, c);

        c.gridx = 4; p.add(new JLabel("Hours:"), c);
        c.gridx = 5; p.add(txtHoursWorked, c);

        r++;
        c.gridy = r;
        c.gridx = 0; p.add(new JLabel("Stipend:"), c);
        c.gridx = 1; p.add(txtStipend, c);

        JButton addBtn = new JButton("Add Employee");
        addBtn.addActionListener(e -> addEmployee());

        r++;
        c.gridy = r;
        c.gridx = 0;
        c.gridwidth = 2;
        p.add(addBtn, c);

        cmbType.addActionListener(e -> setTypeFieldVisibility());

        return p;
    }

    private JPanel buildActions() {
        JPanel p = new JPanel();

        JButton show = new JButton("Show All");
        show.addActionListener(e -> showAll());

        JButton search = new JButton("Search");
        search.addActionListener(e -> search());

        p.add(show);
        p.add(new JLabel("ID:"));
        p.add(txtSearchId);
        p.add(search);

        return p;
    }

    private void setTypeFieldVisibility() {
        String type = (String) cmbType.getSelectedItem();

        txtMonthlySalary.setEnabled("Full-Time".equals(type));
        txtHourlyRate.setEnabled("Part-Time".equals(type));
        txtHoursWorked.setEnabled("Part-Time".equals(type));
        txtStipend.setEnabled("Intern".equals(type));
    }

    // ADD EMPLOYEE 
    private void addEmployee() {
    try {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();

        if (id.isEmpty() || name.isEmpty())
            throw new IllegalArgumentException("ID and Name required");

        Address address = new Address(
                txtCity.getText().trim(),
                txtSubCity.getText().trim(),
                txtCountry.getText().trim()
        );

        Department dept = new Department(txtDept.getText().trim());

        String type = (String) cmbType.getSelectedItem();
        Employee emp;

        if ("Full-Time".equals(type)) {

            double sal = txtMonthlySalary.getText().trim().isEmpty()
                    ? 0
                    : Double.parseDouble(txtMonthlySalary.getText().trim());

            emp = new FullTimeEmployee(id, name, address, dept, sal);

        } else if ("Part-Time".equals(type)) {

            double rate = txtHourlyRate.getText().trim().isEmpty()
                    ? 0
                    : Double.parseDouble(txtHourlyRate.getText().trim());

            int hours = txtHoursWorked.getText().trim().isEmpty()
                    ? 0
                    : Integer.parseInt(txtHoursWorked.getText().trim());

            emp = new PartTimeEmployee(id, name, address, dept, rate, hours);

        } else {

            double stipend = txtStipend.getText().trim().isEmpty()
                    ? 0
                    : Double.parseDouble(txtStipend.getText().trim());

            emp = new Intern(id, name, address, dept, stipend);
        }

        service.addEmployee(emp);
        output.append("Added: " + emp.toDisplayString() + "\n");

        clearFields();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}
    // SHOW ALL 
    private void showAll() {
        output.append("\n--- ALL EMPLOYEES ---\n");
        for (Employee e : service.getAll()) {
            output.append(e.toDisplayString() + "\n");
        }
    }

    // SEARCH 
    private void search() {
    String id = txtSearchId.getText().trim();

    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Enter ID first");
        return;
    }

    Employee e = service.findById(id);

    output.append("\n--- SEARCH RESULT ---\n");
    if (e == null) output.append("Not Found\n");
    else output.append(e.toDisplayString() + "\n");
}

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtMonthlySalary.setText("");
        txtHourlyRate.setText("");
        txtHoursWorked.setText("");
        txtStipend.setText("");
    }

    // MAIN
    public static void main(String[] args) {
        new EmployeeManagementGUI().setVisible(true);
    }
}
