package main.dal.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.users.WarehouseUsersDAO;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserId;
import main.models.userModels.inputModels.UserTypeStatus;

@Component
public class WarehouseUsersDAL implements WarehouseUsersDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	// Saving Warehouse User Information
	@Transactional
	public void saveUser(User user) {
		try {
			entityManager.persist(user); // Save the User object in the database
		} catch (Exception e) {
			throw new IllegalArgumentException("User with the same unique key already exists.");
		}
	}
	
	// Soft Deleting the Warehouse Users
	@Transactional
	public User deleteUser(UserId user) {
		User existingUser = entityManager.find(User.class, user.getUserId()); // Find the User object with the given userId
		existingUser.setStatus("Inactive"); // Set the status of the existing User object to "Inactive"
		entityManager.merge(existingUser); // Merge the changes into the database
		return existingUser; // Return the updated User object
	}
	
	// Getting all Active Warehouse Users
	@Transactional
	public List<User> getAllActiveUsers() {
		List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE status = 'active'").getResultList(); // Retrieve all User objects with status "active"
		return users; // Return the list of active User objects
	}
	// Getting all Warehouse Users
		@Transactional
		public List<User> getAllUsers() {
			List<User> listUsers = entityManager.createQuery("SELECT u FROM User u").getResultList(); // Retrieve all User objects from the database
			return listUsers; // Return the list of User objects
		}
	//Getting User data based on the selected user type and status
	@Override
	public List<UserTypeStatus> getUserByTypeStatus(UserTypeStatus userTypeStatus) {
		// TODO Auto-generated method stub
		List<UserTypeStatus> userTypeStatusList = entityManager.createQuery("SELECT u FROM User u WHERE u.status =:userStatus and u.userType=:userType ").setParameter("userStatus",userTypeStatus.getUserStatus()).setParameter("userType",userTypeStatus.getUserType()).getResultList(); // Retrieve all User objects with status "active"
        return userTypeStatusList;
	}
   //getting user data based on UserStatus
	@Override
	public List<UserTypeStatus> getFilteredDataByStatus(UserTypeStatus userTypeStatus) {
		// TODO Auto-generated method stub
		List<UserTypeStatus> userTypeStatusList = entityManager.createQuery("SELECT u FROM User u WHERE u.status =:userStatus").setParameter("userStatus",userTypeStatus.getUserStatus()).getResultList(); // Retrieve all User objects with status "active"
		return userTypeStatusList;
	}
   //getting user Data based on userType
	@Override
	public List<UserTypeStatus> getFilteredDataByType(UserTypeStatus userTypeStatus) {
		List<UserTypeStatus> userTypeStatusList = entityManager.createQuery("SELECT u FROM User u WHERE u.userType=:userType").setParameter("userType",userTypeStatus.getUserType()).getResultList(); // Retrieve all User objects with status "active"
		return userTypeStatusList;
	}
	

}
