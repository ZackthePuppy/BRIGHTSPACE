public class EmployeeDriver {
    public static void main(String[] args) throws Exception {
        Employee employee[] = new Employee [8];
        employee [2] = new Hourly().load("name", "sss", 7, 7, 3, 3);
        System.out.println("Hello, World!");
        System.out.println(employee[2]);
    }
}
