package com.jsp.medimart.Exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.jsp.medimart.util.SuccessResponse;

@RestControllerAdvice
public class MedimartExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlICVE(SQLIntegrityConstraintViolationException e) {

		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message("you can't perform this operation")
				.data(e.getMessage())
				.dateTime(LocalDate.now())
				.build();
				//
				return new ResponseEntity<SuccessResponse>(data, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<SuccessResponse> notFound(NotFoundException e) {
		
		SuccessResponse data=SuccessResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message("you can't perform this operation")
				.data(e.getMessage())
				.dateTime(LocalDate.now())
				.build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.BAD_REQUEST);
	}
	
	
}