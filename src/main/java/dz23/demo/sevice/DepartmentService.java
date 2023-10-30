package dz23.demo.sevice;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    //нати сотрудника с мин ЗП по номеру отдела
    public Employee findMinSalaryByDepartment(int numberOfDepartment);

    //нати сотрудника с max ЗП по номеру отдела
    public Employee findMaxSalaryByDepartment(int numberOfDepartment);

    //сумма затрат на ЗП по отделу
    public int getTotalSalaryCostByDepartment(int department);

    //все сотрудники по отделу
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int department);

    //все сотрудники вцелом
    public Map<Integer, List<Employee>> getAllEmployees();
}

