package edu.nus.microservice.user_manager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.nus.microservice.user_manager.model.UserRole;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RoleRepository extends CrudRepository<UserRole, Integer> {

	// 使用 @Query 明确定义 SQL 查询语句
	@Query(value = "SELECT * FROM user_role WHERE role_id = ?1", nativeQuery = true)
	UserRole SearchUserRole(int RoleId);
}
