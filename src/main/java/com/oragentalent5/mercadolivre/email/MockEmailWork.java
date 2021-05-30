package com.oragentalent5.mercadolivre.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.oragentalent5.mercadolivre.domain.Compra;

public class MockEmailWork extends NFEmailAbstract {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailWork.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulação Envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado!");
		
	}





}
