package main.bll.login;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.models.loginModel.inputModels.password;
import main.models.userModels.entities.User;
import main.service.procurement.ProcurementService;

@Component
public class PasswordChangeBLL {
	@Autowired
	ProcurementService x;

	public String changePassword(password p) {
		User s = x.getRow(p);
		System.out.println(s.toString());
		if (!s.getOtpExpiryTime().isAfter(LocalDateTime.now())) {
			System.out.println(s.getOtpExpiryTime());
			return "failed";
		} else if (s.getOtp().equals(p.getOtp())) {
			x.getRow2(p);
			return "success";
		} else {
			return "failed";
		}
	}

}
