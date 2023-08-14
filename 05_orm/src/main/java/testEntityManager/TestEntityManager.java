package testEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Banco;
import modelo.CuentaBancaria;
import modelo.Persona;

public class TestEntityManager {

	public static void main(String[] args) {

		// insertar();
		// leer();
		// actualizar();
		// eliminar();
		// consultarJPQL();
		//consultarJPQLNativa();
		consultarJPQLNamed();

	}

	public static void insertar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();

		// Insertar una Persona
		Persona p1 = new Persona(1, "Carlos", "carlos123");

		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();

		// Insertar Banco
		Banco Pichincha = new Banco(1, "Pichincha");

		em.getTransaction().begin();
		em.persist(Pichincha);
		em.getTransaction().commit();

		// Insertar CuentaBancaria
		CuentaBancaria cuenta = new CuentaBancaria(1, "1234567", Pichincha, p1);

		em.getTransaction().begin();
		em.persist(cuenta);
		em.getTransaction().commit();
	}

	public static void leer() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();

		// Leer la persona con id 1
		Persona personaBuscada = em.find(Persona.class, 1);
		System.out.println(personaBuscada);

	}

	public static void actualizar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();

		// Leeo la persona con id 1
		Persona personaBuscada = em.find(Persona.class, 1);
		personaBuscada.setClave("carlos456");

		em.getTransaction().begin();
		em.merge(personaBuscada);
		em.getTransaction().commit();

	}

	public static void eliminar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();
		/*
		 * Persona p2 = new Persona(2, "Mario", "mario123");
		 * em.getTransaction().begin(); em.persist(p2); em.getTransaction().commit();
		 */

		// Leeo la persona con id 1
		Persona personaBuscada = em.find(Persona.class, 2);

		em.getTransaction().begin();
		em.remove(personaBuscada);
		em.getTransaction().commit();

	}

	public static void consultarJPQL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();

		// Seleccionar todas las personas
		String sentenciJPQL = "SELECT p FROM Persona p";
		Query query1 = em.createQuery(sentenciJPQL);
		// Parametros ....

		List<Persona> personas = query1.getResultList();

		for (Persona persona : personas) {
			System.out.println(persona.getClave());
		}

		// Seleccionar la persona que cumple con usuario="Carlos" y clave="carlos456"

		// Seleccionar todas las personas *************************************
		String sentencia = "SELECT p FROM Persona p WHERE p.nombre= :nombre AND p.clave= :clave";

		Query query = em.createQuery(sentencia);
		// Parametros ....
		query.setParameter("nombre", "Carlos");
		query.setParameter("clave", "carlos456");

		Persona personaAutorizada = (Persona) query.getSingleResult();
		System.out.println("Consulta dificilíiiiiiiiiiizima");
		System.out.println(personaAutorizada);

	}

	public static void consultarJPQLNativa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();

		String SQLNativo = "SELECT * FROM Personaa where nombre='Carlos' and clave ='carlos456'";
		Query query = em.createNativeQuery(SQLNativo, Persona.class);
		Persona p = (Persona) query.getSingleResult();
		System.out.println(p);
	}

	public static void consultarJPQLNamed() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carlitosjpa");
		EntityManager em = emf.createEntityManager();
		
		//String sentencia = "SELECT p FROM Persona p WHERE p.nombre= :nombre AND p.clave= :clave";

		Query query = em.createNamedQuery("autorizar");
		// Parametros ....
		query.setParameter("nombre", "Carlos");
		query.setParameter("clave", "carlos456");

		Persona personaAutorizada = (Persona) query.getSingleResult();
		System.out.println("Consulta dificilíiiiiiiiiiizima");
		System.out.println(personaAutorizada);
	}
}
