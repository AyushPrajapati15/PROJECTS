package com.jsp.medimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.Service.AdminService;
import com.jsp.medimart.Service.MemberService;
import com.jsp.medimart.util.MedimartMailSender;
import com.jsp.medimart.util.SuccessResponse;

@RestController
@RequestMapping("/admin")  //base URL FOR THIS CLASS
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	AdminService adminService;
	
//	@Autowired
//	MedimartMailSender mailSender;
	
//	@GetMapping("/send")
//	public void sendMail(@RequestParam String email) {
//		mailSender.requestEnable(email);
//	}
	
	@PostMapping("/registeradmin")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);

	}

	@PostMapping("/updateadmin")
	public ResponseEntity<SuccessResponse> updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
		
	}
	@GetMapping("/fetchadmin")
	public ResponseEntity<SuccessResponse> fetchAdmin(@RequestParam int id) {
		return adminService.fetchAdmin(id);
	}

	@GetMapping("/loginadmin")
	public ResponseEntity<SuccessResponse> loginAdmin(@RequestParam String email, String password) {
		return adminService.adminLogin(email, password);
	}
	
	
	@DeleteMapping("/deleteadmin")
	public ResponseEntity<SuccessResponse> delteAdmin(@RequestParam int id) {
		return adminService.adminDelete(id);
	}
	
	@PutMapping("/enablemember")
	public ResponseEntity<SuccessResponse> enableMember(@RequestParam int adminId,@RequestParam int memberId) {
		return adminService.enableMemberByAdmin(adminId,memberId);
	}
	
	@GetMapping("/getallmember")
	public ResponseEntity<SuccessResponse> getAllMembers() {
		return memberService.fetchAllMember();

	}
	
	
	
	
}
