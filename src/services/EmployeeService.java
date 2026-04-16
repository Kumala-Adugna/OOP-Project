package services;

import employee.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private final ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    // ✅ ADD THIS
    public void deleteById(String id) {
        employees.removeIf(e -> e.getId().equals(id));
    }
}
