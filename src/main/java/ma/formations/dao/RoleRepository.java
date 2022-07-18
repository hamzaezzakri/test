package ma.formations.dao;

import ma.formations.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/1/2022 9:22 PM
 */

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRole(String role);
    boolean existsByRole(String role);
}
