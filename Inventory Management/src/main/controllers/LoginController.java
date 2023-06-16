package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.dao.login.LoginDAO;
import main.models.loginModel.Login;

@Controller
public class LoginController {
	@Autowired
	LoginDAO loginDAO;

	@RequestMapping(value = { "/", "/loginPage" })
	public String getLogin() {
		return "Login/login";
	}

	@PostMapping("/validation")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("userType") String userType, RedirectAttributes redirectAttributes) {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		login.setUserType(userType);
		boolean log = loginDAO.login(login);
		if (log) {
			if (userType.equals("admin")) {
				return "admin/adminHome";
			} else if (userType.equals("inventory")) {
				return "inventory/inventoryHome";
			} else if (userType.equals("procurement")) {
				return "procurement/homeProcurement";
			} else {
				return "login/login";
			}

		}
		return "error";
	}

}
