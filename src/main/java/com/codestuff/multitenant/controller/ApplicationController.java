package com.codestuff.multitenant.controller;

import com.codestuff.multitenant.service.tenant.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.codestuff.multitenant.entity.Employee;
import com.codestuff.multitenant.service.data.EmployeeService;


@RestController
public class ApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(path="/",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public String index(@RequestBody Employee employee, @RequestHeader("DATABASE") String dbName) {
		LOGGER.info("Message from controller.");
		TenantContext.setDbName(dbName);
		String id = employeeService.add(employee);
		String message = "[EMPID="+id+" NAME="+employee.getName()+" PLACE="+employee.getPlace()+"] [DATABASE="+dbName+"]";
		return message;
    }
}
