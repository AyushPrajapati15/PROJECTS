package com.jsp.medimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medimart.Service.OrderService;
import com.jsp.medimart.util.SuccessResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/placeorder")
	public ResponseEntity<SuccessResponse> placeOrder(@RequestParam int memId, @RequestBody List<String> drug) {
		return orderService.order(memId, drug);

	}

}
