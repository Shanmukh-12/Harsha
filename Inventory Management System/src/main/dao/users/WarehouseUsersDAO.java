package main.dao.users;

import java.util.List;

import main.dto.users.UserDto;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserData;

public interface WarehouseUsersDAO {
	public void saveUser(User userData);
	public List<User> getAllUsers();
	public User getUserData(UserDto v);
	public User deleteUser(User user);
	public List<User> getAllActiveUsers();

}
