package dz23.demo.sevice;

import dz23.demo.exceptions.EmployeeAlreadyAddedException;
import dz23.demo.exceptions.EmployeeNotFoundException;
import dz23.demo.exceptions.MaximumEmployeesException;
import dz23.demo.exceptions.NotValidCharacterException;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeeMap;
    public static final int MAX_EMPLOYEES = 3;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Collection<Employee> getEmployeeMap() {
        return this.employeeMap.values();
    }

    @Override
    public Employee addEmployee(String name, String surname, int salary, int department) {
        isCorrectInput(name, surname);

        if (employeeMap.size() >= MAX_EMPLOYEES) {
            throw new MaximumEmployeesException("Максимальное количество сотрудников");
        }
        Employee employee = new Employee(name, surname, salary, department);
        if (!employeeMap.containsKey(employee.getName() + employee.getSurname())) {
            employeeMap.put(employee.getName() + employee.getSurname(), employee);
            return employee;
        }

        throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
    }

    @Override
    public Employee removeEmployee(String name, String surname) {
        isCorrectInput(name, surname);
        if (employeeMap.containsKey(name + surname)) {
            employeeMap.remove(name + surname);
            return new Employee(name, surname);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee findEmployee(String name, String surname) {
        isCorrectInput(name, surname);
        if (!employeeMap.containsKey(name + surname)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return new Employee(name, surname);
    }

    private void isCorrectInput(String name, String surname) {
        HttpParser StringUtils = null;
        if (!StringUtils.isAlpha(Integer.parseInt(name + surname))) {
            throw new NotValidCharacterException("Недопустимые символы в имени или фамилии");
        }
    }
}
