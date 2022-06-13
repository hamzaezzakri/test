package ma.formations.dao;

import ma.formations.service.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 9:27 PM
 */

public interface EmpRepository extends JpaRepository<Emp,Long> {

    List<Emp> findBySalary(Double salary);
    List<Emp> findByFonction(String fonction);
    List<Emp> findBySalaryAndFonction(Double salary, String fonction);
    @Query("SELECT e from Emp e where e.salary=(select MAX(salary) as salary FROM Emp)")
    Emp getEmpHavaingMaxSalary();
}
