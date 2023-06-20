package main.bll.login;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.models.loginModel.inputModels.password;
import main.models.loginModel.outputmodels.AuthOutput;
import main.models.userModels.entities.User;
import main.models.userModels.outputModels.UserOutput;
import main.service.procurement.ProcurementService;

@Component
public class PasswordChangeBLL {
	@Autowired
	ProcurementService x;
	@Autowired
	AuthOutput ao;

	public AuthOutput changePassword(password p) {
		UserOutput s = x.getRow(p);
		System.out.println(s.toString());
		if (!s.getOtpExpiryTime().isAfter(LocalDateTime.now())) {
			System.out.println(s.getOtpExpiryTime());
			ao.setAuthent("failed");
			return ao;
			
		} else if (s.getOtp().equals(p.getOtp())) {
			x.getRow2(p);
			ao.setAuthent("success");

			return ao;
		} else {
			ao.setAuthent("failed");
			return ao;
		}
	}

}
