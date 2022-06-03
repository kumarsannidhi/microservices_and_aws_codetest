package com.zensar.PathologyService.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.zensar.PathologyService.models.Disease;
import com.zensar.PathologyService.models.DiseasesList;

@RestController
@RequestMapping("/pathology")
public class PathologyResource {
	
	private List<Disease> diseases = Arrays.asList(
		new Disease("1", "heartattack", "stunts"),
		new Disease("2", "sayatica pain", "tablets"),
		new Disease("3", "fever", "tablets")
	);
	
	
	@RequestMapping("/diseases")
	public DiseasesList getDiseases() {
		DiseasesList diseasesList = new DiseasesList();
		diseasesList.setDiseases(diseases);
		return diseasesList;
	}
	
	@RequestMapping("/diseases/{Id}")
	public Disease getDiseaseById(@PathVariable("Id") String Id) {
		
		Disease d = diseases.stream()
			.filter(disease -> Id.equals(disease.getId()))
			.findAny()
			.orElse(null);	
		
		return d;
	}	
}