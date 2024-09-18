package edu.nus.microservice.user_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.nus.microservice.user_manager.model.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<EventUser, Integer> {

	@Query(value = "select event_user.* from event_user where user_id=?1 and password=?2 ",nativeQuery = true)
	List<EventUser> checkUserLogin(int UserId,String Password);
	
	@Query(value = "Update event_user set password=?2,email_address=?3,user_role=?4 where user_id=?1",nativeQuery = true)
	void UpdateUser(int UserId,String Password, String UserName, String EmailAddress, int UserRole);
	
	@Query(value = "select event_user.* from event_user where user_id=?1",nativeQuery = true)
	EventUser SearchEventUser(int UserId);
	
}
