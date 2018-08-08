package com.codestuff.multitenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="EMPLOYEE")
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @Column(name="EMP_ID")
    private String empId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="PLACE")
    private String place;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
