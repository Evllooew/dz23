package dz23.demo.controller;

import dz23.demo.sevice.Employee;
import dz23.demo.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam int salary,
                                @RequestParam int department) {
        return employeeService.addEmployee(name, surname, salary, department);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String name, @RequestParam String surname) {
        return employeeService.removeEmployee(name, surname);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String name, @RequestParam String surname) {
        return employeeService.findEmployee(name, surname);
    }

   /* @GetMapping("/departments")
    public String printDepartmentsAndNames() {
        return employeeService.printAllDepartmentsAndNames();
    }*/

    @GetMapping()
    public Collection showEmployee() {
        return employeeService.getEmployeeMap();
    }
}
