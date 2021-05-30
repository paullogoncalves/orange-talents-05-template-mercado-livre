package com.oragentalent5.mercadolivre.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.oragentalent5.mercadolivre.domain.Compra;

public abstract class NFEmailAbstract implements EnvioEmailNotaFiscalWork{

	@Value("${default.sender}")
	private String from;

	@Override
	public void emailConfirmacao(Compra obj) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromNotafiscal(obj);
		sendEmail(sm);
	}
	
	@Override
	public void falhou(Compra obj1) {
		SimpleMailMessage sm1 = prepareSimpleMailMessageFalha(obj1);
		sendEmail(sm1);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromNotafiscal(Compra obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUsuario().getLogin());
		sm.setFrom(from);
		sm.setSubject("Nota fiscal Compra");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		return sm;
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFalha(Compra obj1) {
		SimpleMailMessage sm1 = new SimpleMailMessage();
		sm1.setTo(obj1.getUsuario().getLogin());
		sm1.setFrom(from);
		sm1.setSubject("Deu RUIMMMMM");
		sm1.setSentDate(new Date(System.currentTimeMillis()));
		return sm1;
	}

}
