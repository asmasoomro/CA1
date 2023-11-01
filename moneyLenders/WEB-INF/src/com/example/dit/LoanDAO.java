package com.example.dit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Loan;


	public class LoanDAO {
		
		protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("mydb");

		public LoanDAO() {
			// TODO Auto-generated constructor stub
		}
		 	

		public void persist(Loan loan) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(loan);
			em.getTransaction().commit();
			em.close();
		}
		
		public void removeLoan(Loan loan) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(loan));
			em.getTransaction().commit();
			em.close();
		}
		
		public Loan merge(Loan loan) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Loan updatedLoan = em.merge(loan);
			em.getTransaction().commit();
			em.close();
			return updatedLoan;
		}
		
		
	}
