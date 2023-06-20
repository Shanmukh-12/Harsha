package main.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

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
import main.dto.users.UserDto;
import main.models.productModels.inputModels.CategoryRequest;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserData;
import main.models.userModels.outputModels.UserInfo;


@Controller
public class UserController {

	@Autowired
	public WarehouseUsersDAO userDAO;

	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/createUser")
	public String showCreateForm(Model model) {
		model.addAttribute("user", new User());
		return "admin/addUser";
	}

	@PostMapping("/saveUser")
	public  String saveUser(String data, Model model) {
		
		UserData userData = null;
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			userData = objectMapper.readValue(data,UserData.class);
		} catch (Exception e) {e.printStackTrace();}
		
		User u = mapper.map(userData,User.class);
		
		userDAO.saveUser(u);
		return "admin/addUser";

	}

	@GetMapping("/showUsers")
	public String createUser(Model m) {
		System.out.println("Hiii");
		List<User> users = userDAO.getAllUsers();
		List<UserInfo> ui = new ArrayList();
		for(User u : users)
		{
			ui.add(mapper.map(u, UserInfo.class));
		}
		m.addAttribute("users", ui);
		return "admin/userData";
	}

	@RequestMapping(value = "/getUserData", method = RequestMethod.POST)
	@ResponseBody
	public User getUserData(@RequestBody UserDto v) {
		User user = userDAO.getUserData(v);
		return user;
	}

}
