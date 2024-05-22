package uz.kibera.officeemployees.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.kibera.officeemployees.dto.EmployeeDTO;
import uz.kibera.officeemployees.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO getById(int id);

    List<EmployeeDTO> getAll();

    void save(EmployeeDTO employeeDTO);

    void update(EmployeeDTO employeeDTO);

    void deleteById(int id);

    void getEmployeeExcel(HttpServletResponse response) throws Exception;

    void multipleDelete(List<Integer> ids);
}
