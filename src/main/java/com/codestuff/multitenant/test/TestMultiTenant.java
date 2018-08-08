package com.codestuff.multitenant.test;

import com.codestuff.multitenant.entity.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMultiTenant {

    public static void main(String args[]){
        RestTemplate rt = new RestTemplate();

        Employee employee = new Employee();
        employee.setEmpId(System.currentTimeMillis()+"");
        employee.setName("Sujit");
        employee.setPlace("Hyderabad");

        HttpHeaders headers = new HttpHeaders();
        headers.set("DATABASE","CS_aaaaa");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity= new HttpEntity(employee,headers);

        ResponseEntity<String> out = rt.exchange("http://localhost:8080/", HttpMethod.POST, entity, String.class);
        System.out.println(out);

    }
}
