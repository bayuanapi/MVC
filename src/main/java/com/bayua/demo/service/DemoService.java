package com.bayua.demo.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.bayua.demo.entity.M_EMPLOYEE;
import com.bayua.demo.entity.M_POSITION;
import com.bayua.demo.entity.V_EMPLOYEE;

public interface DemoService {

	List<V_EMPLOYEE> getAllVEmp();

	List<M_POSITION> getListPosition();

	Boolean addObject(Object emp);

	M_EMPLOYEE getMEmpById(String id);

	void deleteEmployee(M_EMPLOYEE emp);

	void saveUpdateObject(Object emp);

}
