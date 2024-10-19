package com.jsp.medimart.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Drug;
import com.jsp.medimart.Repository.DrugRepo;

@Repository
public class DrugDao {
	
	@Autowired
	DrugRepo drugRepo;
	
	//ADD DRUG
	public Drug addDrug(Drug drug) {
		return drugRepo.save(drug);
	}
	
	//UPDATE DRUG
	public Drug updateDrug(Drug drug) {
		Optional<Drug> byId = drugRepo.findById(drug.getId());
		if(byId!=null) {
			return drugRepo.save(drug);			
		}
		return null;
	}
	
	//DELETE DRUG
	public Drug deleteDrug(int id) {
		Optional<Drug> op = drugRepo.findById(id);
		if (op.isPresent()) {
			Drug drug = op.get();
			drugRepo.delete(drug);	
			return drug;
		} else {
			return null;
		}
	}
	
	//FETCH DRUG BY ID
	public Drug fetchDrugById(int id) {
		Optional<Drug> byId = drugRepo.findById(id);
		if(byId.isPresent()) {
			Drug drug = byId.get();
			return drug;
		}
		return null;

	}
	
	//FETCH DRUG BY NAME
	public Drug fetchDrugByName(String name) {
		Optional<Drug> byName = drugRepo.findByName(name);
		if (byName.isPresent()) {
			Drug drug = byName.get();
			return drug;			
		}
		return null;
		
	}
	
	//FETCH ALL DRUG
	public List<Drug> fetchAllDrug() {
		List<Drug> all = drugRepo.findAll();
		return all;

	}
	
	

}
