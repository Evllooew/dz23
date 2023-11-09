package dz23.demo.sevice;

import dz23.demo.exceptions.EmployeeAlreadyAddedException;
import dz23.demo.exceptions.EmployeeNotFoundException;
import dz23.demo.exceptions.MaximumEmployeesException;
import dz23.demo.exceptions.NotValidCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setEmployeeService() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void testAddEmployee() {
        String name = "Klava";
        String surname = "Koka";
        int salary = 1000;
        int department = 1;
        Employee addedEmployee = employeeService.addEmployee(name, surname, salary, department);
        assertNotNull(addedEmployee);
        assertEquals(name, addedEmployee.getName());
        assertEquals(surname, addedEmployee.getSurname());
        assertEquals(salary, addedEmployee.getSalary());
        assertEquals(department, addedEmployee.getDepartment());

        Collection<Employee> collections = employeeService.getEmployeeMap();
        assertTrue(collections.contains(addedEmployee));
    }

    @Test
    void testAddDuplicateEmployee() {
        String name = "Klava";
        String surname = "Koka";
        int salary = 1000;
        int department = 1;
        Employee addedEmployee = employeeService.addEmployee(name, surname, salary, department);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(name, surname, salary, department));
    }

    @Test
    void testAddMaxEmployees() {
        for (int i = 0; i < EmployeeServiceImpl.MAX_EMPLOYEES; i++) {
            String name = "Klava" + ((char) (97 + i));
            String surname = "Koka" + ((char) (97 + i));
            int salary = 1000 + i;
            int department = 1 + i;

            employeeService.addEmployee(name, surname, salary, department);

        }
        assertThrows(MaximumEmployeesException.class, () ->
                employeeService.addEmployee("Fiodor", "Koniuhov", 2, 1));
    }

    @Test
    void testRemoveEmployee() {
        String name = "Klava";
        String surname = "Koka";
        int salary = 1000;
        int department = 1;
        Employee addedEmployee = employeeService.addEmployee(name, surname, salary, department);
        Employee deletedEmployee = employeeService.removeEmployee(name, surname);

        assertNotNull(deletedEmployee);
        assertEquals(name, deletedEmployee.getName());
        assertEquals(surname, deletedEmployee.getSurname());

        Collection<Employee> employeeCollection = employeeService.getEmployeeMap();
        assertFalse(employeeCollection.contains(addedEmployee));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(name, surname));
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("James", "Hatfield"));
    }

    @Test
    void testFindEmployee() {
        String name = "Bill";
        String surname = "Chipper";
        int salary = 100500;
        int department = 13;

        Employee addedEmployee = employeeService.addEmployee(name, surname, salary, department);
        Employee foundedEmployee = employeeService.findEmployee(name, surname);

        assertNotNull(foundedEmployee);
        assertEquals(name, foundedEmployee.getName());
        assertEquals(surname, foundedEmployee.getSurname());

        Collection<Employee> employeeCollection = employeeService.getEmployeeMap();
        assertTrue(employeeCollection.contains(addedEmployee));


        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Frodo", "Baggins"));
    }

    @Test
    void inputValidationTest() {

        String invalidName = "4otkii$%^";
        String invalidSurname = "Ne ochen";

        assertThrows(NotValidCharacterException.class, () ->
                employeeService.findEmployee(invalidName, invalidSurname));
    }
}