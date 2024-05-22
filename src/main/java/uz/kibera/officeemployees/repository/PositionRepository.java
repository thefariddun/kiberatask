package uz.kibera.officeemployees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kibera.officeemployees.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
