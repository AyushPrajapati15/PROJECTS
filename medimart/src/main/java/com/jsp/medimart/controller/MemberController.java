package com.jsp.medimart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.Service.MemberService;
import com.jsp.medimart.util.SuccessResponse;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@PostMapping("/registermember")
	public ResponseEntity<SuccessResponse> saveMember(@RequestBody Member member) {
		return memberService.saveMember(member);

	}

	@PostMapping("/updatemember")
	public ResponseEntity<SuccessResponse> updateMember(@RequestBody Member member) {
		return memberService.updateMember(member);

	}

	@GetMapping("/fetchmember")
	public ResponseEntity<SuccessResponse> fetchMember(@RequestParam int id) {
		return memberService.fetchMember(id);
	}

	@GetMapping("/loginmember")
	public ResponseEntity<SuccessResponse> loginMember(@RequestParam String email, String password) {
		return memberService.memberLogin(email, password);
	}

	@DeleteMapping("/deletemember")
	public ResponseEntity<SuccessResponse> delteMember(@RequestParam int id) {
		return memberService.MemberDelete(id);
	}

	@GetMapping("/allmember")
	public ResponseEntity<SuccessResponse> allMember() {
		return memberService.fetchAllMember();

	}

	@PostMapping("/uploadprofile")
	public ResponseEntity<SuccessResponse> uploadProfile(@RequestParam int memId, @RequestParam MultipartFile file)
			throws IOException {

		// System.out.println(file.getBytes());
		// System.out.println(file.getOriginalFilename());
		return memberService.uploadImage(memId, file);
	}

	@GetMapping("/getprofile")
	public ResponseEntity<byte[]> getProfile(@RequestParam int memId) {
		return memberService.fetchImage(memId);

	}
}

// promise sync
