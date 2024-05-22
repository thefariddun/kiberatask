package uz.kibera.officeemployees.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.kibera.officeemployees.model.Employee;
import uz.kibera.officeemployees.repository.PositionRepository;

import java.util.List;

@Component
public class EmployeeDTOUtil {

    @Autowired
    private PositionRepository positionRepository;

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPositionId(employee.getPosition().getId());

        return employeeDTO;
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(positionRepository.findById(employeeDTO.getPositionId()).orElse(null));
        return employee;
    }

    public List<EmployeeDTO> toDTOs(List<Employee> employees) {
        return employees.stream().map(this::toDTO).toList();
    }
}
