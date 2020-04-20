package com.curso.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		//super.sendEmail(msg);
		Log.info("Simulando envio de email");
		Log.info(msg.toString());
		Log.info("Email enviado");
	}
	

}
