package edu.nus.microservice.user_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.nus.microservice.user_manager.model.EventUser;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<EventUser, UUID> {

	@Query(value = "select event_user.* from event_user where user_id=?1 and password=?2 ",nativeQuery = true)
	List<EventUser> checkUserLogin(int UserId,String Password);
	
	@Query(value = "Update event_user set password=?2,email_address=?3,user_role=?4 where user_id=?1",nativeQuery = true)
	int UpdateUser(UUID UserId, String Password, String UserName, String EmailAddress, int UserRole);

	@Query(value = "Update event_user set password=?1 where email_address=?2",nativeQuery = true)
	void ChangePassword(String Password, String EmailAddress);
	
	@Query(value = "select event_user.* from event_user where user_id=?1",nativeQuery = true)
	EventUser SearchEventUser(UUID UserId);

	//Yikai amend here on 1-Oct, here is important
	@Query(value = "select user_id, user_name, role_id, create_dt, active_status, email_address, password from event_user where email_address=?1 and password=?2",nativeQuery = true)
	EventUser UserLogin(String EmailAddress,String Password);

	@Query(value = "select event_user.* from event_user where email_address=?1",nativeQuery = true)
	EventUser SearchEventbyEmail(String EmailAddress);

	@Query(value = "select event_user.* from event_user where user_name=?1",nativeQuery = true)
	EventUser SearchEventbyUserName(String UserName);

	void deleteById(UUID userId);

}
