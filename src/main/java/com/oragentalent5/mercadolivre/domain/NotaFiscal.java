package com.oragentalent5.mercadolivre.domain;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotaFiscal implements EventoCompraSucesso{

	@Override
	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> req = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/notas-fiscais", req, String.class);

	}

}
