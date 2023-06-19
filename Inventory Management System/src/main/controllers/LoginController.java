package main.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.bll.login.PasswordChangeBLL;
import main.bll.login.RandomNumberBLL;
import main.bll.login.SendingEmailBLL;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.loginModel.outputmodels.AuthOutput;
import main.service.procurement.ProcurementService;

@Controller
public class LoginController {
	@Autowired
	ProcurementService x;
	@Autowired
	RandomNumberBLL rn;
	@Autowired
	SendingEmailBLL sm;
	@Autowired
	PasswordChangeBLL cp;
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
	public String changepass(password p) throws JsonProcessingException {
		AuthOutput ao = cp.changePassword(p);
		ObjectMapper ob=new ObjectMapper();
		String json=ob.writeValueAsString(ao);
		System.out.println(json);
		return json;

	}

	@RequestMapping(value = "/sendotp", method = RequestMethod.GET)
	public String sendmail(MailDetails md, Model m) throws MessagingException {
	
	
		m.addAttribute("mail", md.getMail());
		sm.sendEmail(md);

		return "login/ChangePassword";

	}



	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String check(MailDetails md, Model m) throws MessagingException, JsonProcessingException {
		System.out.println("hello");
		System.out.println("mail" + md.getMail());
		ObjectMapper ob=new ObjectMapper();
		String json=ob.writeValueAsString(x.check(md));
		System.out.println(json);
		return json;
	

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public String login(credentials2 md, Model m) throws MessagingException, JsonProcessingException {
		System.out.println("hello");
		System.out.println(x.getAuthent(md));
		ObjectMapper ob=new ObjectMapper();
		String json=ob.writeValueAsString(x.getAuthent(md));
		System.out.println(json);
		return json;

	}
}
