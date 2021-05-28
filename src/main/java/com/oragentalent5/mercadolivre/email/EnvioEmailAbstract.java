package com.oragentalent5.mercadolivre.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.oragentalent5.mercadolivre.domain.Pergunta;

public abstract class EnvioEmailAbstract implements EnvioEmailWork {

	@Value("${default.sender}")
	private String from;
	
	@Override
	public void sendPerguntaEmail(Pergunta obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPergunta(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPergunta(Pergunta obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUsuario().getLogin());
		sm.setFrom(from);
		sm.setSubject(obj.getTitulo());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}
}
