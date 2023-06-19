package main.dal.indents;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.models.indentModels.Indents;

@Component
public class IndentsDal {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void createIndent(Indents indent) {
		entityManager.persist(indent);
	}

	@Transactional
	public List<Indents> getAllIndents() {
		List<Indents> l = entityManager.createQuery("SELECT v FROM Indents v").getResultList();
		for (Indents v : l) {
			System.out.println(v.toString());
		}
		return l;
	}

}