package com.jsp.medimart.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
	
	private String message;

	
	

}
