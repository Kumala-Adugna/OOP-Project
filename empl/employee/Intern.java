package employee;

public class Intern extends Employee {

    private double stipend;

    public Intern(String id, String name, Address address,
                  Department department, double stipend) {
        super(id, name, address, department, stipend );
        this.stipend = stipend;
    }

    @Override
    public double calculateSalary() {
        return stipend;
    }

    @Override
    public String getType() {
        return "Intern";
    }
}
