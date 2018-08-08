package com.codestuff.multitenant.service.data;

import com.codestuff.multitenant.entity.Employee;

public interface EmployeeService {
	
	public Employee getEmployee(String empId);
	
	public String add(Employee employee);
}
