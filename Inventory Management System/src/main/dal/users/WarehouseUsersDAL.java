package main.dal.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.dao.users.WarehouseUsersDAO;
import main.models.userModels.entities.User;
import main.models.userModels.inputModels.UserId;

@Component
public class WarehouseUsersDAL implements WarehouseUsersDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void saveUser(User user) {
		try {
			entityManager.persist(user);
		} catch (Exception e) {
			throw new IllegalArgumentException("User with the same unique key already exists.");
		}
	}

	@Transactional
	public List<User> getAllUsers() {
		List<User> l = entityManager.createQuery("SELECT u FROM User u").getResultList();
		for (User u : l)
			System.out.println(u);
		return l;
	}


	@Transactional
	public User deleteUser(UserId user) {
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
