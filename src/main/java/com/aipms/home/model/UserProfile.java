package com.aipms.home.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userName;
	@Column(unique=true)
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
	private String secQuestion;
	private String secAnswer;

	
}
