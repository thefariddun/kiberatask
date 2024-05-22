package uz.kibera.officeemployees.dto;

import org.springframework.stereotype.Component;
import uz.kibera.officeemployees.model.Employee;
import uz.kibera.officeemployees.model.Position;

import java.util.List;

@Component
public class PositionDTOUtil {

    public PositionDTO toDTO(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setName(position.getName());
        positionDTO.setDepartment(position.getDepartment());
        positionDTO.setDescription(position.getDescription());
        positionDTO.setSalary(position.getSalary());
        positionDTO.setLocation(position.getLocation());

        return positionDTO;
    }

    public Position toEntity(PositionDTO positionDTO) {
        Position position = new Position();
        position.setId(positionDTO.getId());
        position.setName(positionDTO.getName());
        position.setDepartment(positionDTO.getDepartment());
        position.setDescription(positionDTO.getDescription());
        position.setSalary(positionDTO.getSalary());
        position.setLocation(positionDTO.getLocation());

        return position;
    }

    public List<PositionDTO> toDTOs(List<Position> positions) {
        return positions.stream().map(this::toDTO).toList();
    }
}
