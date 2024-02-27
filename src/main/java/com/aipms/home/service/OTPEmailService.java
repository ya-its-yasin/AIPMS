package com.aipms.home.service;

import org.springframework.stereotype.Service;

@Service
public interface OTPEmailService {

	public boolean sendSimpleMail(int otp, String mailId);
}
