package dev.ayanfe.emsbackend.repository;

import dev.ayanfe.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
