package com.aipms.home.model;

import java.util.Date;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

	@Id
	private int userId;
	private String userName;
	private String emailId;
	private long mobileNumber;
	private String password;
	private Date DOB;
	private int age;
	private String gender;
	private String Nationality;
	private String address;
	private String nomineeName;
	private String aadharNumber;
	private String panNumber;
	private double walletBalance;
	//private Map secretQuestions;
}
