package modelo.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.dao.PersonaDAO;
import modelo.entidades.Persona;

public class JPAPersonaDAO extends JPAGenericDAO<Persona, Integer> implements PersonaDAO {

	public JPAPersonaDAO() {
		super(Persona.class);
	}

	@Override
	public Persona autorizar(String nombre, String clave) {
		String sentencia = "SELECT p FROM Persona p WHERE p.nombre= :nombre AND p.password= :clave";

		Query query = em.createQuery(sentencia);
		// Parametros ....
		query.setParameter("nombre", nombre);
		query.setParameter("clave", clave);

		Persona personaAutorizada = (Persona) query.getSingleResult();
		return personaAutorizada;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> getAll() {
		return this.em.createQuery("SELECT p FROM Persona p").getResultList();
	}

}
