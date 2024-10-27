package com.jsp.medimart.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medimart.DAO.AdminDao;
import com.jsp.medimart.DAO.MemberDao;
import com.jsp.medimart.Exception.NotFoundException;
import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.util.SuccessResponse;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;

	@Autowired
	MemberDao memberDao;

	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin) {

		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.CREATED.value()).dateTime(LocalDate.now())
				.data(adminDao.saveAdmin(admin)).message("admin saved successful").build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);

	}

	public ResponseEntity<SuccessResponse> updateAdmin(Admin admin) {

		SuccessResponse data = SuccessResponse.builder().status(HttpStatus.CREATED.value()).dateTime(LocalDate.now())
				.data(adminDao.updateAdmin(admin)).message("admin updated successful").build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);

	}

	public ResponseEntity<SuccessResponse> fetchAdmin(int id) {
		Admin fetchAdmin = adminDao.fetchAdmin(id);
		if (fetchAdmin != null) {

			SuccessResponse data = SuccessResponse.builder().status(HttpStatus.FOUND.value()).dateTime(LocalDate.now())
					.data(adminDao.fetchAdmin(id)).message("admin found successful").build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
		}
//		SuccessResponse error = SuccessResponse.builder()
//	            .status(HttpStatus.NOT_FOUND.value())
//	            .dateTime(LocalDate.now())
//	            .message("Admin not found")
//	            .build();
//	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		throw new NotFoundException("Admin with " + id + " not found");
	}

	public ResponseEntity<SuccessResponse> adminLogin(String email, String password) {
		Admin loginAdmin = adminDao.loginAdmin(email, password);
		if (loginAdmin != null) {
			if (loginAdmin.getEmailid().equalsIgnoreCase(email)) {

				if (loginAdmin.getPassword().equalsIgnoreCase(password)) {

					SuccessResponse data = SuccessResponse.builder().status(HttpStatus.FOUND.value())
							.dateTime(LocalDate.now()).data(adminDao.loginAdmin(email, password))
							.message("admin login successful").build();
					return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
				} else {
					throw new NotFoundException("Admin  with password " + loginAdmin.getPassword() + " not found");
				}
			} else {
				throw new NotFoundException("Admin with email: " + email + " not found");
			}
		}
		throw new NotFoundException("Admin with " + email + " and " + password + " not found");
	}

	public ResponseEntity<SuccessResponse> adminDelete(int id) {
		Admin deleteAdmin = adminDao.deleteAdmin(id);
		if (deleteAdmin != null) {

			SuccessResponse data = SuccessResponse.builder().status(HttpStatus.FOUND.value()).dateTime(LocalDate.now())
					.data(adminDao.deleteAdmin(id)).message("admin d   eleted successful;y").build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
		}
		throw new NotFoundException("Admin with " + id + " not found");
	}

	public ResponseEntity<SuccessResponse> enableMemberByAdmin(int adminId, int memberId) {
		Admin adminDb = adminDao.fetchAdmin(adminId);
		if (adminDb != null) {
			Member memberDb = memberDao.fetchMember(memberId);
			if (memberDb != null) {
				memberDb.setDisable(true);
				memberDao.updateMember(memberDb);

				SuccessResponse data = SuccessResponse.builder().status(HttpStatus.FOUND.value())
						.dateTime(LocalDate.now()).data(memberDb).message("Member enabled successful;y").build();
				return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);

			} else {
				throw new NotFoundException("Member with " + memberId + " not found");

			}
		} else {
			throw new NotFoundException("Member with " + memberId + " not found");

		}
	}

}