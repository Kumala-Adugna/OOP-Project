package employee;

public class FullTimeEmployee extends Employee {

    private double monthlySalary;

    public FullTimeEmployee(String id, String name, Address address,
                            Department department, double monthlySalary) {
        super(id, name, address, department);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    @Override
    public String getType() {
        return "Full-Time";
    }
}