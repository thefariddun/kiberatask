package uz.kibera.officeemployees.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kibera.officeemployees.dto.EmployeeDTO;
import uz.kibera.officeemployees.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/getBy/{id}")
    public EmployeeDTO getById(@PathVariable("id") int id) {
        EmployeeDTO employee = employeeService.getById(id);
        return employee;
    }

    @GetMapping("/employee/getAll")
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    //    public ResponseEntity<Page<Employee>> getEmployees(
//            @RequestParam(defaultValue = "0") int pageNo,
//            @RequestParam(defaultValue = "10") int pageSize) {
//        Page<Employee> employees = employeeService.getAll(pageNo, pageSize);
//        return ResponseEntity.ok(employees);
//    }
    @PostMapping("/employee/save")
    public void saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
    }

    @PutMapping("/employee/update")
    public void updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(employeeDTO);
    }

    @DeleteMapping("/employee/delete/{id}")
    public void deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }

    @DeleteMapping("/multipleDelete")
    public void multipleDelete(@RequestBody List<Integer> ids) {
        employeeService.multipleDelete(ids);
    }

    @GetMapping("/getEmployeeExcel")
    public void getExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=employees.xls";
        response.setHeader(headerKey, headerValue);
        employeeService.getEmployeeExcel(response);
    }
}
