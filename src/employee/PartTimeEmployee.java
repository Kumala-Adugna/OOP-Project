package employee;

public class PartTimeEmployee extends Employee {

    private int hours;

    public PartTimeEmployee(String id, String name, Address address, Department department, double rate, int hours) {
        super(id, name, address, department, rate * hours);
        this.hours = hours;
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }

    @Override
    public String getType() {
        return "Part-Time";
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
