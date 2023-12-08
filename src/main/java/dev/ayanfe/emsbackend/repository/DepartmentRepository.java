package dev.ayanfe.emsbackend.repository;

import dev.ayanfe.emsbackend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
