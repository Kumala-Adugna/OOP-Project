package employee;

public abstract class Employee {

    protected String id;
    protected String name;
    protected Address address;
    protected Department department;
    protected double baseSalary;

    // CONSTRUCTOR
    public Employee(String id, String name, Address address, Department department, double baseSalary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.department = department;
        this.baseSalary = baseSalary;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // SETTERS (IMPORTANT FOR UPDATE FEATURE)
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    // ABSTRACT METHODS (must be implemented by subclasses)
    public abstract double calculateSalary();

    public abstract String getType();

    // DISPLAY FOR TABLE
    public String toDisplayString() {
        return id + " | " + name + " | " + getType() +
                " | " + department.getName() +
                " | " + calculateSalary();
    }
}
