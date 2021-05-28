package com.oragentalent5.mercadolivre.email;

import org.springframework.mail.SimpleMailMessage;

import com.oragentalent5.mercadolivre.domain.Pergunta;

public interface EnvioEmailWork {

	void sendPerguntaEmail(Pergunta pergunta);
	
	void sendEmail(SimpleMailMessage msg);
}
