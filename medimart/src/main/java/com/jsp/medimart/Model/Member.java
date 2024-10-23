package com.jsp.medimart.Model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String gender;
	private LocalDate dob;
	@Lob
	@Column(columnDefinition = "LONGBLOB",length =Integer.MAX_VALUE)
	private byte[] image;
	private String emailid;
	private String password;
	private long mobile;
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	private boolean disable;
	
	
	

}
