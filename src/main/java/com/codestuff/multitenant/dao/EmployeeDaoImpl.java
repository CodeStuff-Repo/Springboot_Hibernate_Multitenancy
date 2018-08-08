package com.codestuff.multitenant.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Component;

import com.codestuff.multitenant.entity.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee find(EntityManager em,String empID) {
		return em.find(Employee.class, empID);
	}

	@Override
	public void add(EntityManager em, Employee employee) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(employee);
			transaction.commit();
		}catch(Exception ex) {
			transaction.rollback();
			em.close();
			ex.printStackTrace();
		}
	}
}
