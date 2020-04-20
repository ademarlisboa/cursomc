package com.curso.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.curso.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	@Value("${default.sender}")
	private String sender;
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		// TODO Auto-generated method stub
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido p) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(p.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado: Código " + p.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(p.toString());
		return sm;
	}
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		
	}
	

}
