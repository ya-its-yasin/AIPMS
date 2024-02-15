package com.aipms.home.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class UserInfo {

	@Id
	private int userId;
	private String userName;
	private long mobileNumber;
	private String emailId;
	private String password;
	
}
