package bm.employee.service;

import bm.employee.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> employeeList();

    public Employee employeeIDSearch(Integer idEmployee);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

}
