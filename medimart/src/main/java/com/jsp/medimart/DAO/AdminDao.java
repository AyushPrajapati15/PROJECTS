package com.jsp.medimart.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Repository.AdminRepo;

@Repository
public class AdminDao {
	@Autowired
	AdminRepo adminRepo;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepo.save(admin);

	}
	public Admin updateAdmin(Admin admin) {
		Optional<Admin> op = adminRepo.findById(admin.getId());
		if(op!=null) {
			return adminRepo.save(admin);			
		}
		return null;
		
	}

	public Admin fetchAdmin(int id) {
		Optional<Admin> op=adminRepo.findById(id);
		if(op.isPresent()){
			Admin admin=op.get();
			return admin;
		}
		return null;
	}
	public Admin loginAdmin(String emailid, String password) {
		Optional<Admin> op=adminRepo.findByEmailidAndPassword(emailid, password);
		if(op.isPresent()){
			Admin admin=op.get();
					return admin;					
		}
		return null;
	}
	public Admin deleteAdmin(int id) {
		Optional<Admin> op=adminRepo.findById(id);
		if(op.isPresent()){
			Admin admin=op.get();
			adminRepo.delete(admin);
			return admin;
		}
		return null;
	}
	
	
	
}
