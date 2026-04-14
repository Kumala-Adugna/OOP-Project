package employee;

import interfaces.Payable;

public abstract class Employee implements Payable {
    private  String id;
    private String name;
    private Address address;
    private Department department;

    public Employee(String id, String name, Address address, Department department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.department = department;
    }
    

    public String getId() { return id; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public Department getDepartment() { return department; }

    public void setName(String name) { this.name = name; }

    public abstract String getType(); // helpful for GUI

    public String toDisplayString() {
        return "ID=" + id +
                ", Name=" + name +
                ", Type=" + getType() +
                ", Dept=" + (department == null ? "N/A" : department) +
                ", Address=" + (address == null ? "N/A" : address) +
                ", Salary=" + calculateSalary();
    }
   
}


