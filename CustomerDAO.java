package com.example.dit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Customer;

	public class CustomerDAO {
		
		protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("jpaPU");

		public CustomerDAO() {
			// TODO Auto-generated constructor stub
		}
		 	

		public void persist(Customer customer) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(customer);
			em.getTransaction().commit();
			em.close();
		}
		
		public void removeCustomer(Customer customer) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(customer));
			em.getTransaction().commit();
			em.close();
		}
		
		public Customer merge(Customer customer) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Customer updatedCustomer = em.merge(customer);
			em.getTransaction().commit();
			em.close();
			return updatedCustomer;
		}
		
		
		public List<Customer> getAllCustomers() {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Customer> customers = new ArrayList<Customer>();
			customers = em.createQuery("from Customer").getResultList();
			em.getTransaction().commit();
			em.close();
			return customers;
		}

		public Customer getCustomerByName(String name) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Customer c = em.createQuery("SELECT p FROM Customer p WHERE p.name = :name", Customer.class)
	                .setParameter("name", name)
	                .getSingleResult();
			em.getTransaction().commit();
			em.close();
			return c;
		}
		


	}
