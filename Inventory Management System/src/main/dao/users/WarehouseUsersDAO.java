package main.dao.users;

import java.util.List;

import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserId;
import main.models.userModels.inputModels.UserTypeStatus;

public interface WarehouseUsersDAO {
	
	// Saving Warehouse User Information i.e.Inventory or Procurement User
	public void saveUser(User user);
	
	// Getting all Warehouse Users
	public List<User> getAllUsers();
	
    //Deleting the WareHouse User i.e.Making Status Inactive
	public User deleteUser(UserId user);
	
    //Getting all Active Warehouse Users
	public List<User> getAllActiveUsers();
	
    //Filter based on userType and UserStatus
	public List<UserTypeStatus> getUserByTypeStatus(UserTypeStatus userTypeStatus);
	
    //Filter Based on UserType
	public List<UserTypeStatus> getFilteredDataByType(UserTypeStatus userTypeStatus);
	
    //filter Based on User Status
	public List<UserTypeStatus> getFilteredDataByStatus(UserTypeStatus userTypeStatus);

}
