package com.codestuff.multitenant.service.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.codestuff.multitenant.dao.EmployeeDao;
import com.codestuff.multitenant.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	/*@Autowired
	private EntityManager entityManager;*/

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee getEmployee(String empId) {
		return this.employeeDao.find(getEntityManager(),empId);
	}

	@Override
	public String add(Employee employee) {
		/*String id = System.currentTimeMillis()+"";
		employee.setEmpId(System.currentTimeMillis()+"");
		employee.setName("Virat");
		employee.setPlace("Delhi");*/
		this.employeeDao.add(getEntityManager(), employee);
		return employee.getEmpId();
	}

	private EntityManager getEntityManager(){
		EntityManagerFactory emf = this.entityManagerFactoryBean.getObject();
		return emf.createEntityManager();
	}

}
