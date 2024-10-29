package com.jsp.medimart.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medimart.DAO.AdminDao;
import com.jsp.medimart.DAO.DrugDao;
import com.jsp.medimart.Exception.NotFoundException;
import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Drug;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.util.SuccessResponse;

@Service
public class DrugService {
	
	@Autowired
	DrugDao drugDao;
	
	@Autowired
	AdminDao adminDao;
	
	
	public boolean checkAdmin(int id) {
		Admin admin = adminDao.fetchAdmin(id);
		if (admin!=null) {
			return true;
			
		}
		return false;
		

	}
	
public ResponseEntity<SuccessResponse> saveDrug(Drug drug,int id) {
	if(checkAdmin(id)) {
		
		
		Drug addDrug = drugDao.addDrug(drug);
		
		 SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDate.now())
				.data(addDrug)
				.message("Member saved successful")
				.build()
				;
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);

	}
	throw new NotFoundException("Admin with "+id+" not found");
}


public ResponseEntity<SuccessResponse> updateDrugById(Drug drug,int id) {
	
	if (checkAdmin(id)) {
	Drug addDrug = drugDao.updateDrug(drug);
	
	SuccessResponse data=SuccessResponse.builder()
			.status(HttpStatus.CREATED.value())
			.dateTime(LocalDate.now())
			.data(addDrug)
			.message("Drug saved successful")
			.build()
			;
	return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
	}
	throw new NotFoundException("Admin with id: "+id+" not found");
}


public ResponseEntity<SuccessResponse> deleteDrugById(int drugId,int adminId) {
	
	if (checkAdmin(adminId)) {
		Drug addDrug = drugDao.deleteDrug(drugId);
		
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDate.now())
				.data(addDrug)
				.message("Drug saved successful")
				.build()
				;
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
	}
	throw new NotFoundException("Admin with id: "+adminId+" not found");
}

public ResponseEntity<SuccessResponse> fetchById(int id) {
	Drug fetchDrugById = drugDao.fetchDrugById(id);
	if (fetchDrugById!=null) {
		
		SuccessResponse data=SuccessResponse.builder()
			.status(HttpStatus.FOUND.value())
				.dateTime(LocalDate.now())
				.data(fetchDrugById)
				.message("Drug found successfully")
				.build();	
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}
	throw new NotFoundException("Drug with id: "+id+" not found");
}

public ResponseEntity<SuccessResponse> fetchByName(String name) {
	Drug fetchDrugByName = drugDao.fetchDrugByName(name);
	if(fetchDrugByName!=null)
	{
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDate.now())
				.data(fetchDrugByName)
				.message("Drug found successfully")
				.build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
	}
	throw new NotFoundException("Drug with name: "+name+" not found");

}

public ResponseEntity<SuccessResponse> fetchAll() {
	List<Drug> fetchAllDrug = drugDao.fetchAllDrug();
	if (fetchAllDrug!=null) {
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDate.now())
				.data(fetchAllDrug)
				.message("Fetched all drug successfully")
				.build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		
	}
	throw new NotFoundException("No drug available");

}






//avental



}
