package uz.kibera.officeemployees.service;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.kibera.officeemployees.dto.EmployeeDTO;
import uz.kibera.officeemployees.dto.EmployeeDTOUtil;
import uz.kibera.officeemployees.model.Employee;
import uz.kibera.officeemployees.repository.EmployeeRepository;
import uz.kibera.officeemployees.repository.PositionRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOUtil employeeDTOUtil;
    private final PositionRepository positionRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTOUtil employeeDTOUtil, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOUtil = employeeDTOUtil;
        this.positionRepository = positionRepository;
    }

    @Override
    public EmployeeDTO getById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employeeDTOUtil.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeDTOUtil.toDTOs(employeeRepository.findAll());
    }

    @Override
    @Transactional
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTOUtil.toEntity(employeeDTO);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeRepository.findById(employeeDTO.getId()).
                orElseThrow(() -> new RuntimeException("Employee not found" + employeeDTO.getId()));
        newEmployee.setId(employeeDTO.getId());
        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setEmail(employeeDTO.getEmail());
        newEmployee.setPosition(positionRepository.findById(employeeDTO.getPositionId()).orElse(null));
        employeeRepository.save(newEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void getEmployeeExcel(HttpServletResponse response) throws Exception {
        List<Employee> employees = employeeRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("All Employees");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("firstName");
        row.createCell(2).setCellValue("lastName");
        row.createCell(3).setCellValue("email");
        row.createCell(4).setCellValue("position");

        int dataRowIndex = 1;
        for (Employee employee : employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(employee.getId());
            dataRow.createCell(1).setCellValue(employee.getFirstName());
            dataRow.createCell(2).setCellValue(employee.getLastName());
            dataRow.createCell(3).setCellValue(employee.getEmail());
            dataRow.createCell(4).setCellValue(employee.getPosition().getName());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    @Override
    @Transactional
    public void multipleDelete(List<Integer> ids) {
        employeeRepository.multipleDelete(ids);
    }
}
