package com.example.dit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.dit.model.Employee;

	public class EmployeeDAO {
		
		protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("mydb");

		public EmployeeDAO() {
			// TODO Auto-generated constructor stub
		}
		 	

		public void persist(Employee employee) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
			em.close();
		}
		
		public void removeEmployee(Employee employee) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(employee));
			em.getTransaction().commit();
			em.close();
		}
		
		public Employee merge(Employee employee) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Employee updatedEmployee = em.merge(employee);
			em.getTransaction().commit();
			em.close();
			return updatedEmployee;
		}
		
		
		public List<Employee> getAllEmployees() {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Employee> employees = new ArrayList<Employee>();
			employees = em.createQuery("from Employee").getResultList();
			em.getTransaction().commit();
			em.close();
			return employees;
		}

		public Employee getEmployeeByName(String employeeName) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Employee e = em.createQuery("SELECT p FROM Employee p WHERE p.employeeName = :employeeName", Employee.class)
	                .setParameter("employeeName", employeeName)
	                .getSingleResult();
			em.getTransaction().commit();
			em.close();
			return e;
		}
		


	}
