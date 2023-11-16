package dz23.demo.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMinSalaryByDepartment(int numberOfDepartment) {
        return employeeService.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == numberOfDepartment)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public Employee findMaxSalaryByDepartment(int numberOfDepartment) {
        return employeeService.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == numberOfDepartment)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public int getTotalSalaryCostByDepartment(int department) {
        return employeeService.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int department) {
        return employeeService.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        //тут объект Map<Integer,List<Employee>>
        return employeeService.getEmployeeMap().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
