package main.dal.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.dao.users.WarehouseUsersDAO;
import main.dto.users.UserDto;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserData;


@Component
public class WarehouseUsersDAL implements WarehouseUsersDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void saveUser(User user) {
		try {
			entityManager.merge(user);
		} catch (Exception e) {
			throw new IllegalArgumentException("User with the same unique key already exists.");
		}
	}

	@Transactional
	public List<User> getAllUsers() {
		System.out.println("Inside this yaar");
		List<User> l = entityManager.createQuery("SELECT u FROM User u").getResultList();
		for(User u:l)
			System.out.println(u);
		return l;
	}

	@Transactional
	public User getUserData(UserDto v) {
		User getUser = entityManager.find(User.class, v.getUserId());
		return getUser;
	}

	@Transactional
	public User deleteUser(User user) {
		User existingUser = entityManager.find(User.class, user.getUserId());
		existingUser.setStatus("Inactive");
		entityManager.merge(existingUser);
		return existingUser;

	}
	@Transactional
	public List<User> getAllActiveUsers() {
		List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE status = 'active'").getResultList();
		return users;
	}

}
