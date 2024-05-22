package uz.kibera.officeemployees.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.kibera.officeemployees.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.id IN :ids")
    void multipleDelete(@Param("ids") List<Integer> ids);

}
