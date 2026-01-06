package bm.employee.service;

import bm.employee.entity.Employee;
import bm.employee.repository.IEmployeeRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{0

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public List<Employee> employeeList() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Employee employeeIDSearch(Integer idEmployee) {
//        or else es por si no encuentra el registro regresa el valor de null
        Employee employee = iEmployeeRepository.findById(idEmployee).orElse(null);
        return employee;

    }

    @Override
    public void saveEmployee(Employee employee) {
        iEmployeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        iEmployeeRepository.delete(employee);

    }
}
