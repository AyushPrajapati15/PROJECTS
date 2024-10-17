package com.jsp.medimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medimart.Model.Drug;
import com.jsp.medimart.Service.DrugService;
import com.jsp.medimart.util.SuccessResponse;

@RestController
@RequestMapping("/drug")
public class DrugController {

	@Autowired
	DrugService drugService;
	
	@PostMapping("/adddrug")
	public ResponseEntity<SuccessResponse> addDrug(@RequestBody Drug drug,@RequestParam int adId) {
		return drugService.saveDrug(drug, adId);

	}
	@PostMapping("/updatedrug")
	public ResponseEntity<SuccessResponse> updateDrug(@RequestBody Drug drug,@RequestParam int adId) {
		return drugService.saveDrug(drug, adId);
		
	}
	@DeleteMapping("/deletedrug")
	public ResponseEntity<SuccessResponse> deleteDrug(@RequestParam int drugId,@RequestParam int adId) {
		return drugService.deleteDrugById(drugId, adId);
		
	}
	
	@GetMapping("/getbyid")
	private ResponseEntity<SuccessResponse> drugById(@RequestParam int id) {
		return drugService.fetchById(id);

	}
	
	@GetMapping("/getbyname")
	private ResponseEntity<SuccessResponse> drugByName(@RequestParam String name) {
		return drugService.fetchByName(name);

	}
	
	@GetMapping("/getall")
	private ResponseEntity<SuccessResponse> allDrug() {
		return drugService.fetchAll();

	}
	
}


//regular expressions