package gui;

import employee.*;
import services.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeManagementGUI extends JFrame {

    private final EmployeeService service = new EmployeeService();

    private final JTextField txtId = new JTextField(10);
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtDept = new JTextField(15);
    private final JTextField txtSalary = new JTextField(10);

    // NEW (Part-Time support)
    private final JTextField txtRate = new JTextField(10);
    private final JTextField txtHours = new JTextField(10);

    private final JTextField txtSearchId = new JTextField(10);

    private final JComboBox<String> cmbType =
            new JComboBox<>(new String[]{"Full-Time", "Part-Time", "Intern"});

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    public EmployeeManagementGUI() {
        super("Employee Management System");

        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(buildForm(), BorderLayout.NORTH);
        add(buildTable(), BorderLayout.CENTER);
        add(buildActions(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel buildForm() {
        JPanel p = new JPanel(new FlowLayout());

        p.add(new JLabel("ID:"));
        p.add(txtId);

        p.add(new JLabel("Name:"));
        p.add(txtName);

        p.add(new JLabel("Type:"));
        p.add(cmbType);

        p.add(new JLabel("Dept:"));
        p.add(txtDept);

        p.add(new JLabel("Salary:"));
        p.add(txtSalary);

        // NEW INPUTS
        p.add(new JLabel("Rate:"));
        p.add(txtRate);

        p.add(new JLabel("Hours:"));
        p.add(txtHours);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> addEmployee());

        p.add(addBtn);

        return p;
    }

    private JScrollPane buildTable() {
        String[] cols = {"ID", "Name", "Type", "Department", "Salary"};

        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        scrollPane.setVisible(false);

        return scrollPane;
    }

    private JPanel buildActions() {
        JPanel p = new JPanel();

        JButton showBtn = new JButton("Show All");
        showBtn.addActionListener(e -> showAll());

        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener(e -> updateEmployee());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteEmployee());

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> searchById());

        p.add(showBtn);

        p.add(new JLabel("ID:"));
        p.add(txtSearchId);
        p.add(searchBtn);

        p.add(updateBtn);
        p.add(deleteBtn);

        return p;
    }

    private void addEmployee() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String dept = txtDept.getText().trim();

            Address address = new Address("", "", "");
            Department department = new Department(dept);

            Employee emp;
            String type = (String) cmbType.getSelectedItem();

            if ("Full-Time".equals(type)) {

                double salary = Double.parseDouble(txtSalary.getText().trim());
                emp = new FullTimeEmployee(id, name, address, department, salary);

            } else if ("Part-Time".equals(type)) {

                double rate = Double.parseDouble(txtRate.getText().trim());
                int hours = Integer.parseInt(txtHours.getText().trim());

                emp = new PartTimeEmployee(id, name, address, department, rate, hours);

            } else {

                double stipend = Double.parseDouble(txtSalary.getText().trim());
                emp = new Intern(id, name, address, department, stipend);
            }

            service.addEmployee(emp);
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void refreshTable() {

        model.setRowCount(0);

        for (Employee e : service.getAll()) {
            model.addRow(new Object[]{
                    e.getId(),
                    e.getName(),
                    e.getType(),
                    e.getDepartment().getName(),
                    e.calculateSalary()
            });
        }

        scrollPane.setVisible(true);
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    private void showAll() {
        refreshTable();
    }

    private void searchById() {
        String id = txtSearchId.getText().trim();

        model.setRowCount(0);

        for (Employee e : service.getAll()) {
            if (e.getId().equals(id)) {
                model.addRow(new Object[]{
                        e.getId(),
                        e.getName(),
                        e.getType(),
                        e.getDepartment().getName(),
                        e.calculateSalary()
                });
                scrollPane.setVisible(true);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Not found");
    }

    private void deleteEmployee() {
        String id = txtSearchId.getText().trim();

        service.deleteById(id);
        refreshTable();
    }

    private void updateEmployee() {
        String id = txtSearchId.getText().trim();

        Employee e = service.findById(id);

        if (e == null) {
            JOptionPane.showMessageDialog(this, "Not found");
            return;
        }

        try {
            e.setName(txtName.getText().trim());
            e.setDepartment(new Department(txtDept.getText().trim()));
            e.setBaseSalary(Double.parseDouble(txtSalary.getText().trim()));

            refreshTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid update input");
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDept.setText("");
        txtSalary.setText("");
        txtRate.setText("");
        txtHours.setText("");
    }

    public static void main(String[] args) {
        new EmployeeManagementGUI().setVisible(true);
    }
}
