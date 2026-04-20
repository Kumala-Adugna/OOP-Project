package employee;

public class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String id, String name, Address address,
                            Department department, double hourlyRate, int hoursWorked) {

        super(id, name, address, department, 0);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String getType() {
        return "Part-Time";
    }
}