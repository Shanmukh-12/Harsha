package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@PostMapping("/createUser")
	public String createNewUser() {

		return "/";
	}
}
