package dz23.demo.sevice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private final EmployeeServiceImpl employeeServiceMock = mock(EmployeeServiceImpl.class);
    private DepartmentServiceImpl out;

    @BeforeEach

    void initOut() {
        out = new DepartmentServiceImpl(employeeServiceMock);
    }

    @Test
    void willReturnEmployeeWithMinSalaryByDepartment() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Ivan", "Dorn", 200, 1));
        employeeList.add(new Employee("Ivan", "Groznii", 300, 1));
        employeeList.add(new Employee("Ivan", "Urgant", 400, 2));

        when(employeeServiceMock.getEmployeeMap()).thenReturn(employeeList);

        Employee result = out.findMinSalaryByDepartment(1);

        assertNotNull(result);

        assertEquals(200, result.getSalary());

        Employee result1 = out.findMinSalaryByDepartment(2);

        assertNotNull(result);

        assertEquals(400, result1.getSalary());
    }
    @Test
    void testFindMinSalaryByDepartmentNoEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        when(employeeServiceMock.getEmployeeMap()).thenReturn(employeeList);

        assertThrows(NoSuchElementException.class, () -> out.findMinSalaryByDepartment(1));
    }
    @Test
    void willReturnEmployeeWithMaxSalaryByDepartment() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Ivan", "Dorn", 200, 1));
        employeeList.add(new Employee("Ivan", "Groznii", 300, 1));
        employeeList.add(new Employee("Ivan", "Urgant", 400, 2));

        when(employeeServiceMock.getEmployeeMap()).thenReturn(employeeList);

        Employee result = out.findMaxSalaryByDepartment(1);

        assertNotNull(result);

        assertEquals(300, result.getSalary());
    }
    @Test
    void testFindMaxSalaryByDepartmentNoEmployee() {
        List<Employee> employeeList = new ArrayList<>();

        when(employeeServiceMock.getEmployeeMap()).thenReturn(employeeList);

        assertThrows(NoSuchElementException.class, () -> out.findMaxSalaryByDepartment(1));
    }


}
