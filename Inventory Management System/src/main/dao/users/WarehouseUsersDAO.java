package main.dao.users;

import java.util.List;

import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserId;

public interface WarehouseUsersDAO {
	public void saveUser(User user);

	public List<User> getAllUsers();

	public User deleteUser(UserId user);

	public List<User> getAllActiveUsers();

}
