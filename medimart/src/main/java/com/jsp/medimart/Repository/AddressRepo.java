package com.jsp.medimart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medimart.Model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
