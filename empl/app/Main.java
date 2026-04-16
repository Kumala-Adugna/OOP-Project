package app;

import gui.EmployeeManagementGUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Management System Running...");
        new EmployeeManagementGUI().setVisible(true);
    }
}