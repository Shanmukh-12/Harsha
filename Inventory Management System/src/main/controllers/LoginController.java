package main.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.bll.login.PasswordChangeBLL;
import main.bll.login.RandomNumberBLL;
import main.bll.login.SendingEmailBLL;
import main.dao.login.LoginDAO;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.loginModel.outputmodels.AuthOutput;
import main.models.userModels.outputModels.UserOutput;
import main.service.procurement.LoginService;
import main.service.procurement.ProcurementService;

@Controller
public class LoginController {

	@Autowired
	RandomNumberBLL rn;
	@Autowired
	SendingEmailBLL sm;
	@Autowired
	PasswordChangeBLL cp;
	@Autowired
	LoginService loginService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home() {
		return "login/login";
	}

	@RequestMapping(value = "/forgotrequest", method = RequestMethod.GET)
	public String request() {
		return "login/OtpReq";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthOutput changepass(password p) {
		AuthOutput ao = cp.changePassword(p);
	    return ao;

	}

	@RequestMapping(value = "/sendotp", method = RequestMethod.GET)
	public String sendmail(MailDetails md, Model m)  {
	
	
		m.addAttribute("mail", md.getMail());
		try {
			sm.sendEmail(md);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "login/ChangePassword";

	}



	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthOutput check(MailDetails md, Model m) throws MessagingException, JsonProcessingException {

		AuthOutput s=loginService.check(md);
		return s;
	

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public AuthOutput login(credentials2 md, Model m) throws MessagingException, JsonProcessingException {
		System.out.println("hello");
		System.out.println(loginService.getAuthent(md));
		AuthOutput s=loginService.getAuthent(md);
		return s;

	}

	//logout controller
	@GetMapping("/logout")
	public String logout() {
		return "login/login";
	}
}
