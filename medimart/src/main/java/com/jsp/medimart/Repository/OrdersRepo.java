package com.jsp.medimart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medimart.Model.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

}
