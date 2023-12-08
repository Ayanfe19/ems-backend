package dev.ayanfe.emsbackend.service.impl;

import dev.ayanfe.emsbackend.dto.DepartmentDto;
import dev.ayanfe.emsbackend.entity.Department;
import dev.ayanfe.emsbackend.exception.ResourceNotFoundException;
import dev.ayanfe.emsbackend.mapper.DepartmentMapper;
import dev.ayanfe.emsbackend.repository.DepartmentRepository;
import dev.ayanfe.emsbackend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }


    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with given id: " + departmentId + " does not exist!"));

        return DepartmentMapper.mapToDepartmentDto(department);
    }


    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment){
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department with given id: " + departmentId + " does not exist!")
        );

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

        Department updatedDepartmentObj = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(updatedDepartmentObj);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department with given id: " + departmentId + " does not exist!")
        );

        departmentRepository.deleteById(departmentId);
    }
}
