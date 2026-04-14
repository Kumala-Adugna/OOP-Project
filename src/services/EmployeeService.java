package services;

import employee.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private final ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        if (findById(e.getId()) != null) {
            throw new IllegalArgumentException("Duplicate ID: " + e.getId());
        }
        employees.add(e);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }
}