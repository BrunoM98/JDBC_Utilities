package bm.employee.repository;

import bm.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
