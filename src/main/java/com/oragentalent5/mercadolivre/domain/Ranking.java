package com.oragentalent5.mercadolivre.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Ranking implements EventoCompraSucesso {

	public void processa(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> req = Map.of("idCompra", compra.getId(), "idVendedor", compra.getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking", req, String.class);
		
	}

}
