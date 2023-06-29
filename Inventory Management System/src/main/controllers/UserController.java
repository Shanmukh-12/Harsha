package main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.users.WarehouseUsersDAO;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserData;
import main.models.userModels.inputModels.UserId;
import main.models.userModels.inputModels.UserTypeStatus;
import main.models.userModels.outputModels.UserIds;
import main.models.userModels.outputModels.UserType;

@Controller
public class UserController {

	@Autowired
	public WarehouseUsersDAO userDAO; // Autowired dependency injection for WarehouseUsersDAO

	@Autowired
	ModelMapper mapper; // Autowired dependency injection for ModelMapper

	// Saving user
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody String data, Model model) {
		UserData userData = null;
		ObjectMapper objectMapper = new ObjectMapper(); // Object mapper for JSON serialization/deserialization
		try {
			userData = objectMapper.readValue(data, UserData.class); // Deserialize the JSON string to UserData object
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		User users = mapper.map(userData, User.class); // Map UserData to User using ModelMapper
		userDAO.saveUser(users); // Save the User object using userDAO
		return "admin/adminHome";
	}

	// Getting User Type
	@PostMapping("/getUser")
	public @ResponseBody List<UserType> deleteUser(Model model) {
		List<User> users = userDAO.getAllUsers(); // Retrieve all users from the database using userDAO

		// Map each User object to UserType using ModelMapper and collect into a list
		List<UserType> userType = users.stream().map(user -> mapper.map(user, UserType.class))
				.collect(Collectors.toList());

		return userType; // Return the list of UserType objects as a JSON response
	}

	// Getting User Id and User Name
	@RequestMapping(value = "/getUserData", method = RequestMethod.POST)
	public @ResponseBody List<UserIds> getUserData(@RequestBody Map<String, String> request) {
		String userType = request.get("userType");
		List<User> users = userDAO.getAllActiveUsers(); // Retrieve all active users from the database using userDAO

		List<UserIds> userIds = new ArrayList<>();

		for (User user : users) {
			if (userType.equals(user.getUserType())) {
				UserIds userId = new UserIds();
				userId.setUserId(user.getUserId());
				userId.setUserName(user.getUserName());
				userIds.add(userId);
			}
		}

		return userIds; // Return the list of UserIds objects as a JSON response
	}

	// Deleting User
	@PostMapping("/deleteUserData")
	public String deleteUser(@RequestBody UserId user) {

		userDAO.deleteUser(user); // Delete the user based on the given UserId using userDAO
		return "admin/success";
	}

	/* Filters */
	// Getting All User Information
	@PostMapping("/showUsers")
	public @ResponseBody List<User> showUsers() {
		return userDAO.getAllUsers(); // Retrieve all users from the database using userDAO and return as a JSON
										// response
	}

	// getting User data based on type and status
	@PostMapping("/getFilteredDataByTypeAndStatus")
	public @ResponseBody List<UserTypeStatus> getByTypeStatus(UserTypeStatus userTypeStatus) {
		List<UserTypeStatus> userTypeStatusList = userDAO.getUserByTypeStatus(userTypeStatus);
		return userTypeStatusList;
	}

	// getting user Data based on type
	@PostMapping("/getFilteredDataByType")
	public @ResponseBody List<UserTypeStatus> getFilteredByType(UserTypeStatus userTypeStatus) {
		List<UserTypeStatus> userTypeStatusList = userDAO.getFilteredDataByType(userTypeStatus);
		return userTypeStatusList;
	}

	// Getting user Data based on user Status
	@PostMapping("/getFilteredDataByStatus")
	public @ResponseBody List<UserTypeStatus> getFilteredByStatus(UserTypeStatus userTypeStatus) {
		List<UserTypeStatus> userTypeStatusList = userDAO.getFilteredDataByStatus(userTypeStatus);
		return userTypeStatusList;
	}
}
