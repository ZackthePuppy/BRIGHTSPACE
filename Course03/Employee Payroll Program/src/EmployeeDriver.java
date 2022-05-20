

public class EmployeeDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Employee employee [] = new Employee [3];
        employee[2] = new Hourly("wew", "aw", 5, 4, 20, 30);
        // Employee hourly = new Hourly();
        // Employee salaried = new Salaried();
        // Employee salariedCommission = new SalariedPlusCommission();

        
        // hourly.load();
        // hourly.load();

        // hourly.getEarnings();
        // hourly.getBonus();

        System.out.println(employee[2]);
    }
}
