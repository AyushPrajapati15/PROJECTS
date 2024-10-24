package com.jsp.medimart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medimart.Model.Drug;

public interface DrugRepo extends JpaRepository<Drug, Integer> {

	Optional<Drug> findByName(String name);

}
