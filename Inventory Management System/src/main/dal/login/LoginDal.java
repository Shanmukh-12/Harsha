package main.dal.login;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.dao.login.LoginDAO;
import main.models.loginModel.entities.Login;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.models.userModels.entities.User;
import main.models.userModels.outputModels.UserOutput;
@Component
public class LoginDal implements LoginDAO{
	@PersistenceContext
	EntityManager manager;

	@Transactional
	public UserOutput check(MailDetails m) {
		Query q = manager.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				m.getMail());
		System.out.println(m.getMail());
		User s = (User) q.getSingleResult();
		ModelMapper mp = new ModelMapper();
		UserOutput s1 = mp.map(s, UserOutput.class);
		System.out.println(s.toString());
		return s1;
	}

	@Transactional
	public void getData(MailDetails m, String num) {
		Query q = manager.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				m.getMail());
		User s = (User) q.getSingleResult();
		s.setOtp(num);
		s.setOtpExpiryTime((LocalDateTime.now().plusMinutes(2)));
		System.out.println(s.toString());

	}

	@Transactional
	public UserOutput getRow(password p) {
		Query q = manager.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				p.getMail());
		User s = (User) q.getSingleResult();
		ModelMapper mp = new ModelMapper();
		UserOutput s1 = mp.map(s, UserOutput.class);

		System.out.println(s.toString());
		return s1;

	}

	@Transactional
	public void getRow2(password p) {
		Query q = manager.createQuery("select s from User s where s.userName=:email", User.class).setParameter("email",
				p.getMail());
		User s = (User) q.getSingleResult();
		s.setUserPassword(p.getPass());
		System.out.println(p.getPass());
		System.out.println(s.toString());

	}

	@Transactional
	public UserOutput getAuthent(credentials2 s) {
		Query q = manager.createQuery(
				"select s from User s where s.userName=:email and s.userPassword=:password and s.userType=:usertype",
				User.class).setParameter("email", s.getUsername()).setParameter("password", s.getPassword())
				.setParameter("usertype", s.getUser_type());
		User ud = (User) q.getSingleResult();
		ModelMapper mp = new ModelMapper();
		UserOutput s1 = mp.map(ud, UserOutput.class);
		System.out.println(ud.toString());
		return s1;

	}

}
