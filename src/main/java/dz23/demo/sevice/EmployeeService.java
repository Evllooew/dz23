package dz23.demo.sevice;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String name, String surname, int salary, int department);

    Employee removeEmployee(String name, String surname);

    Employee findEmployee(String name, String surname);

    Collection<Employee> getEmployeeMap();
}
