package com.codestuff.multitenant.dao;

import javax.persistence.EntityManager;

import com.codestuff.multitenant.entity.Employee;

public interface EmployeeDao{

	public Employee find(EntityManager em,String empId);
	
	public void add(EntityManager em, Employee employee);
}
