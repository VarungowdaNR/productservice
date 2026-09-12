package com.ecomm.product.service;

public interface MailSender {
	
	void sendMail(String to,String subject,String body);

}
