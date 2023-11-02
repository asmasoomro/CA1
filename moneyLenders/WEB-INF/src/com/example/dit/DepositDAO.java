package com.example.dit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Customer;
import com.example.dit.model.Deposit;

	public class DepositDAO {
		
		protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("jpaPU");

		public DepositDAO() {
			// TODO Auto-generated constructor stub
		}
		 	

		public void persist(Deposit deposit) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(deposit);
			em.getTransaction().commit();
			em.close();
		}
		
		public void removeDeposit(Deposit deposit) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(deposit));
			em.getTransaction().commit();
			em.close();
		}
		
		public Deposit merge(Deposit deposit) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Deposit updatedDeposit = em.merge(deposit);
			em.getTransaction().commit();
			em.close();
			return updatedDeposit;
		}
		
		public Deposit getDepositById(int id) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Deposit d = em.createQuery("SELECT p FROM Deposit p WHERE p.id = :id", Deposit.class)
	                .setParameter("id", id)
	                .getSingleResult();
			em.getTransaction().commit();
			em.close();
			return d;
		}
		

	}
