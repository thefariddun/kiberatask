package uz.kibera.officeemployees.service;

import jakarta.servlet.http.HttpServletResponse;
import uz.kibera.officeemployees.dto.EmployeeDTO;
import uz.kibera.officeemployees.dto.PositionDTO;

import java.util.List;

public interface PositionService {

    PositionDTO getById(int id);

    List<PositionDTO> getAll();

    void save(PositionDTO positionDTO);

    void update(PositionDTO positionDTO);
}
