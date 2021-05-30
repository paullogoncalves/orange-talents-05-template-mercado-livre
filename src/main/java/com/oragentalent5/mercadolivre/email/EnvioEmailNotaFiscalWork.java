package com.oragentalent5.mercadolivre.email;

import org.springframework.mail.SimpleMailMessage;

import com.oragentalent5.mercadolivre.domain.Compra;
import com.oragentalent5.mercadolivre.domain.Pergunta;

public interface EnvioEmailNotaFiscalWork {
	
	void emailConfirmacao(Compra compra);

	void sendEmail(SimpleMailMessage msg);

	void falhou(Compra compra);
	
}
