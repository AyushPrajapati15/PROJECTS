package com.jsp.medimart.util;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse {
	
	private int status;
	private String message;
	private LocalDate dateTime;
	private Object data;

}









//enum