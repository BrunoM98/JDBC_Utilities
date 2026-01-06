package bm.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  En DB se crea como id_Employee
    Integer idEmployee;
    String nameEmployee;
    String department;
    Double salary;


}
