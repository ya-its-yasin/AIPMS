package com.aipms.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.aipms.home.service.OTPEmailService;

@Service
public class OTPEmailServiceImpl implements OTPEmailService{

	@Autowired 
	JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") 
    String sender;

    public boolean sendSimpleMail(int otp, String mailId)
    {
         try {
        	SimpleMailMessage mailMessage = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(mailId);
            mailMessage.setText("Your OTP for changing password is " + otp);
            mailMessage.setSubject("Forgot password AIPMS");
 
            javaMailSender.send(mailMessage);
            return true;
            
        }catch (Exception e) {
            return false;
        }
    }
}
