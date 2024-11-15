package com.jsp.medimart.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medimart.DAO.DrugDao;
import com.jsp.medimart.DAO.MemberDao;
import com.jsp.medimart.DAO.OrdersDao;
import com.jsp.medimart.Exception.NotFoundException;
import com.jsp.medimart.Model.Drug;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.Model.Orders;
import com.jsp.medimart.util.MedimartMailSender;
import com.jsp.medimart.util.SuccessResponse;

@Service
public class OrderService {

	@Autowired
	MemberDao memberDao;

	@Autowired
	DrugDao drugDao;

	@Autowired
	OrdersDao ordersDao;

	@Autowired
	Orders orders;

	@Autowired
	MedimartMailSender sender;

	@SuppressWarnings("unused")
	public ResponseEntity<SuccessResponse> order(int mem_id, List<String> drugName) {
		Member memberDb = memberDao.fetchMember(mem_id);
		String email = memberDb.getEmailid();
		String memName = memberDb.getName();
		List<Drug> list = new ArrayList<Drug>();
		double total = 0;
		if (memberDb != null) {
			for (String drug : drugName) {
				Drug drugDb = drugDao.fetchDrugByName(drug);
				if (drugDb != null) {
					if (drugDb.getQuantity() != 0) {
						list.add(drugDb);
						total += drugDb.getPrice();

					} else {
						throw new NotFoundException("Drug is out of stock");
					}

				} else {
					throw new NotFoundException("Drug with name " + drug + " not found");
				}
			}
			orders.setMemberid(mem_id);
			orders.setOrderamount(total);
			orders.setDrug(list);
			ordersDao.saveOrder(orders);
			// sending mail for order placed successfully
			sender.orderPlaced(email, memName, orders);
			SuccessResponse data = SuccessResponse.builder().status(HttpStatus.FOUND.value()).dateTime(LocalDate.now())
					.data(list).message("order placed successfully").build();

			return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
		} else {
			throw new NotFoundException("Member with id: " + mem_id + " not found");
		}

	}
}
