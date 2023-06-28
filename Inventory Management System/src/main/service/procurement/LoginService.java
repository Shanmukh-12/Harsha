package main.service.procurement;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.dao.login.LoginDAO;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.loginModel.outputmodels.AuthOutput;
import main.models.userModels.outputModels.UserOutput;
@Component
public class LoginService {
	@Autowired
	LoginDAO loginDAO;
	@Autowired
	AuthOutput ao;
	public AuthOutput check(MailDetails m) {
		try {
			UserOutput s = loginDAO.check(m);
		} catch (NoResultException e) {
			// No entity found, return false
			ao.setAuthent("false");
			return ao;
		}

		ao.setAuthent("true");
		return ao;

	}

	public AuthOutput getAuthent(credentials2 c) {
		try {
			UserOutput s = loginDAO.getAuthent(c);
		} catch (NoResultException e) {
			// No entity found, return false
		     ao.setAuthent("login failed");
			return ao;
		}
		ao.setAuthent("login success");

		return ao;
	}

	public void getDat(MailDetails m, String num) {
		System.out.println("hello");
		loginDAO.getData(m, num);
		System.out.println("hello");
	}

	public UserOutput getRow(password m) {
		return loginDAO.getRow(m);
	}

	public void getRow2(password p) {
		loginDAO.getRow2(p);

	}

}
