package com.jsp.medimart.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medimart.DAO.MemberDao;
import com.jsp.medimart.Exception.NotFoundException;
import com.jsp.medimart.Model.Admin;
import com.jsp.medimart.Model.Member;
import com.jsp.medimart.util.MedimartMailSender;
import com.jsp.medimart.util.SuccessResponse;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	MedimartMailSender sender;
	//REGISTER MEMBER
	public ResponseEntity<SuccessResponse> saveMember(Member member) {
		
		Member saveMember = memberDao.saveMember(member);
		
		 SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDate.now())
				.data(saveMember)
				.message("Member saved successful")
				.build()
				;
		 if (saveMember!=null) {
			 sender.requestEnableMember(saveMember);
			
		}
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);

	}
	
	
//	UPDATE MEMBER DETAILS
public ResponseEntity<SuccessResponse> updateMember(Member member) {
		
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.CREATED.value())
				.dateTime(LocalDate.now())
				.data(memberDao.updateMember(member))
				.message("Member updated successful")
				.build()
				;
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<SuccessResponse> fetchMember(int id) {
		Member fetchMember = memberDao.fetchMember(id);
		if(fetchMember!=null) {
			
		
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.FOUND.value())
				.dateTime(LocalDate.now())
				.data(memberDao.fetchMember(id))
				.message("Member found successful")
				.build()
				;
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}
		throw new NotFoundException("Member with "+id+" not found");
	}
	
	//MEMBER LOGIN
	public ResponseEntity<SuccessResponse> memberLogin(String email,String password) {
		Member loginMember = memberDao.loginMember(email, password);
		if(loginMember!=null) {
			
			if (loginMember.getEmailid().equalsIgnoreCase(email)) {
				
				if ((loginMember.getPassword().equalsIgnoreCase(password))&&(loginMember.isDisable())) {
					
					SuccessResponse data=SuccessResponse.builder()
							.status(HttpStatus.FOUND.value())
							.dateTime(LocalDate.now())
							.data(memberDao.loginMember(email, password))
							.message("Member login successful")
							.build()
							;
					return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);					
					
				} else {	
					throw new NotFoundException("In-valid member password");

				}
				
				
			} else {
				throw new NotFoundException("In-valid member e-mail");

			}
		}
		throw new NotFoundException("Member with "+email+" and "+password+" not found");
	}
	
	//DELETE MEMBER
	public ResponseEntity<SuccessResponse> MemberDelete(int id) {
		  Member deleteMember = memberDao.deleteMember(id);
		if(deleteMember!=null) {
			
			
			SuccessResponse data=SuccessResponse.builder()
					.status(HttpStatus.FOUND.value())
					.dateTime(LocalDate.now())
					.data(memberDao.deleteMember(id))
					.message("admin deleted successful")
					.build()
					;
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		}
		throw new NotFoundException("Member with "+id+" not found");
	}
	
	
	//ALL MEMBER DETAILS
	public ResponseEntity<SuccessResponse> fetchAllMember() {
		
		 List<Member> allMember = memberDao.allMember();
		 if(allMember!=null)
		 {
			 SuccessResponse data=SuccessResponse.builder()
						.status(HttpStatus.FOUND.value())
						.dateTime(LocalDate.now())
						.data(memberDao.allMember())
						.message("members found successfully")
						.build()
						;
				return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		 }
		  throw new NotFoundException("No Member Found");
		 
	}
	
	public ResponseEntity<SuccessResponse> uploadImage(int memId,MultipartFile file) throws IOException {
		Member memberDb = memberDao.fetchMember(memId);
		if(memberDb!=null) {
			memberDb.setImage(file.getBytes());
			memberDao.updateMember(memberDb);
			SuccessResponse data=SuccessResponse.builder()
					.status(HttpStatus.CREATED.value())
					.dateTime(LocalDate.now())
					.data(memberDao.updateMember(memberDb))
					.message("members Updated successfully")
					.build()
					;
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
		}
		throw new NotFoundException("Member with id: "+memId+" not found");
	}
	
	public ResponseEntity<byte[]> fetchImage(int memid) {
		Member memberDb = memberDao.fetchMember(memid);
		if(memberDb!=null) {
			byte[] image = memberDb.getImage();
			HttpHeaders headers=new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(image,headers,HttpStatus.FOUND);
		}
		throw new NotFoundException("Member with id: "+memid+" Not found");

	}
	
}
