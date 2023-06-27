package main.dao.login;

import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.userModels.outputModels.UserOutput;

public interface LoginDAO {
	public UserOutput check(MailDetails m);
	public void getData(MailDetails m, String num);
	public UserOutput getRow(password p);
	public void getRow2(password p);
	public UserOutput getAuthent(credentials2 s);
}
