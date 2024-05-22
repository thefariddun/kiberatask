package uz.kibera.officeemployees.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kibera.officeemployees.dto.EmployeeDTO;
import uz.kibera.officeemployees.dto.PositionDTO;
import uz.kibera.officeemployees.service.PositionService;

import java.util.List;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;


    @GetMapping("/position/getBy/{id}")
    public PositionDTO getById(@PathVariable("id") int id) {
        PositionDTO position = positionService.getById(id);
        return position;
    }

    @GetMapping("/position/getAll")
    public List<PositionDTO> getAllEmployees() {
        return positionService.getAll();
    }

    @PostMapping("/position/save")
    public void saveEmployee(@RequestBody PositionDTO positionDTO) {
        positionService.save(positionDTO);
    }

    @PutMapping("/position/update")
    public void updateEmployee(@RequestBody PositionDTO positionDTO) {
        positionService.update(positionDTO);
    }
}
