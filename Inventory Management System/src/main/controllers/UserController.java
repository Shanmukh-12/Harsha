package main.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import main.models.userModels.outputModels.UserIds;
import main.models.userModels.outputModels.UserType;


@Controller
public class UserController {

	@Autowired
	public WarehouseUsersDAO userDAO;

	@Autowired
	ModelMapper mapper;
	
    //Saving user 
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody String data, Model model) {
		UserData userData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			userData = objectMapper.readValue(data, UserData.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		User s = mapper.map(userData, User.class);
		userDAO.saveUser(s);
		return "admin/adminHome";

	}
	//Getting User Type
		@PostMapping("/getUser")
		public @ResponseBody List<UserType> deleteUser(Model model) {

			List<User> users = userDAO.getAllUsers();

			List<UserType> userType= users.stream().map(user -> mapper.map(user, UserType.class))
					.collect(Collectors.toList());

			return userType;
		}
      //Getting User Id and User Name
		@RequestMapping(value = "/getUserData", method = RequestMethod.POST)
		public @ResponseBody List<UserIds> getUserData(@RequestBody Map<String, String> request) {
		    String userType = request.get("userType");
		    List<User> users = userDAO.getAllActiveUsers();
		    
		    List<UserIds> userIds = new ArrayList<>();
		    
		    for (User user : users) {
		        if (userType.equals(user.getUserType())) {
		            UserIds userId = new UserIds();
		            userId.setUserId(user.getUserId());
		            userId.setUserName(user.getUserName());
		            userIds.add(userId);
		        }
		    }
		    
		    return userIds;
		}
        //Deleting User
		@PostMapping("/deleteUserData")
		public String deleteUser(@RequestBody UserId user) {
			userDAO.deleteUser(user);
			return "admin/success";
		}
	   //Getting User Information
		@GetMapping("/showUsers")
		public @ResponseBody List<User> showUsers() {
			return userDAO.getAllUsers();
		}
}
