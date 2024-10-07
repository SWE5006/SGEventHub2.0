package edu.nus.microservice.user_manager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.nus.microservice.user_manager.model.UserRole;
import org.springframework.transaction.annotation.Transactional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Transactional
public interface RoleRepository extends CrudRepository<UserRole, Integer> {
	@Query(
			value = "SELECT user_role.* FROM user_role where role_id=?1",
			nativeQuery = true
	)
	UserRole SearchUserRole(int RoleId);
	Iterable<UserRole> findAll();
}
