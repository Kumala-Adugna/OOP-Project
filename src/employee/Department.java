package employee;

public class Department {
    private String name;

    public Department(String name) { 
        this.name = name; 
    }

    @Override
    public String toString() {
        return (name == null || name.isBlank()) ? "N/A" : name;
    }
}
