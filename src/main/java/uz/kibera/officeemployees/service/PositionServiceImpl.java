package uz.kibera.officeemployees.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.kibera.officeemployees.dto.PositionDTO;
import uz.kibera.officeemployees.dto.PositionDTOUtil;
import uz.kibera.officeemployees.model.Employee;
import uz.kibera.officeemployees.model.Position;
import uz.kibera.officeemployees.repository.PositionRepository;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionDTOUtil positionDTOUtil;

    public PositionServiceImpl(PositionRepository positionRepository, PositionDTOUtil positionDTOUtil) {
        this.positionRepository = positionRepository;
        this.positionDTOUtil = positionDTOUtil;
    }


    @Override
    public PositionDTO getById(int id) {
        Position position = positionRepository.findById(id).orElse(null);
        return positionDTOUtil.toDTO(position);
    }

    @Override
    public List<PositionDTO> getAll() {
        return positionDTOUtil.toDTOs(positionRepository.findAll());
    }

    @Override
    @Transactional
    public void save(PositionDTO positionDTO) {
        Position position = positionDTOUtil.toEntity(positionDTO);
        positionRepository.save(position);
    }

    @Override
    @Transactional
    public void update(PositionDTO positionDTO) {
        Position newPosition = positionRepository.findById(positionDTO.getId()).
                orElseThrow(() -> new RuntimeException("Employee not found" + positionDTO.getId()));
        newPosition.setId(positionDTO.getId());
        newPosition.setName(positionDTO.getName());
        newPosition.setDepartment(positionDTO.getDepartment());
        newPosition.setDescription(positionDTO.getDescription());
        newPosition.setSalary(positionDTO.getSalary());
        newPosition.setLocation(positionDTO.getLocation());

        positionRepository.save(newPosition);
    }

}
