package com.jsp.medimart.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medimart.Model.Orders;
import com.jsp.medimart.Repository.OrdersRepo;


@Repository
public class OrdersDao {
	
	@Autowired
	OrdersRepo ordersRepo;
	
	
	public Orders saveOrder(Orders orders) {
		return ordersRepo.save(orders);
		

	}
	
	

}
