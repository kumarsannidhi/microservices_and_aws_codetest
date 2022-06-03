package com.zensar.AdmissionsService.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.AdmissionsService.model.DiseasesList;
import com.zensar.AdmissionsService.model.EmployeesList;
import com.zensar.AdmissionsService.model.Patient;



@RestController
@RequestMapping("/admissions")
public class AdmissionsResource {
	
	@Autowired
	
	private RestTemplate restTemplate;
	
	List<Patient> patients = Arrays.asList(				
		new Patient("1", "bala murali", "India"),
		new Patient("2", "manoj", "srilanka"),
		new Patient("3", "lokesh", "nepal")
		);
	
	@RequestMapping("/patients")
	public List<Patient> getPatients() {
		return patients;
	}
	
	@RequestMapping("/patients/{Id}")
	public Patient getPatientById(@PathVariable("Id") String Id) {
		Patient p = patients.stream()
				.filter(patient -> Id.equals(patient.getId()))
				.findAny()
				.orElse(null);
		return p;
	}
	
	
	@RequestMapping("/physicians")
	public EmployeesList getPhysicians() {
		EmployeesList physicians = 
				restTemplate.getForObject("http://hr-service/hr/employees", EmployeesList.class);
		return physicians;
	}
	
	@RequestMapping("/diseases")
	public DiseasesList getDiseases() {
		DiseasesList diseases = 
				restTemplate.getForObject("http://pathology-service/pathology/diseases", DiseasesList.class);
				return diseases;
	}	
}