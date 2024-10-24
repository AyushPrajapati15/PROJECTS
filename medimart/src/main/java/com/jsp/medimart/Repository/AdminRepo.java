package com.jsp.medimart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.medimart.Model.Admin;
import java.util.List;


public interface AdminRepo extends JpaRepository<Admin, Integer> {

//	@Query(select a A from a where a.id=?1)
	Optional<Admin> findById(int id);
	
	Optional<Admin> findByEmailidAndPassword(String emailid , String password);
	
	
	

}
