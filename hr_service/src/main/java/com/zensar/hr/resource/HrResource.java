package com.zensar.hr.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.hr.model.Employee;
import com.zensar.hr.model.EmployeesList;

@RestController
	@RequestMapping("/hr")
	public class HrResource {
		
		
		List<Employee> employees = Arrays.asList(
			new Employee("emp1", "kumar", "sannidhi", "cardiology"),
			new Employee("emp2", "pavan", "kumar", "neurology"),
			new Employee("emp3", "nihash", "sai", "pathology")
		);
		
		@RequestMapping("/employees")
		public EmployeesList getEmployees() {
			
			EmployeesList employeesList = new EmployeesList();
			employeesList.setEmployees(employees);		
			return employeesList;
		}
		
		@RequestMapping("/employees/{Id}")
		public Employee getEmployeeById(@PathVariable("Id") String Id) {
			Employee e = employees.stream()
					.filter(employee ->Id.equals(employee.getId()))
					.findAny()
					.orElse(null);
			return e;
		}		
	}

