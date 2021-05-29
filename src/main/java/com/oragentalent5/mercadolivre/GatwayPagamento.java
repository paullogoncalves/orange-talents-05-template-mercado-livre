package com.oragentalent5.mercadolivre;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.oragentalent5.mercadolivre.domain.Compra;

public enum GatwayPagamento {

	pagseguro {
		@Override
		public String escolhaPagamento(UriComponentsBuilder uriComponentsBuilder, Long id) {
			UriComponents urlPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
					.buildAndExpand(id.toString());
			return "pagSeguro.com/" + id + "?redirectURl=" + urlPagSeguro;
		}
	},
	paypal {

		@Override
		public String escolhaPagamento(UriComponentsBuilder uriComponentsBuilder, Long id) {
			UriComponents urlPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(id.toString());
			return "paypal.com/" + id + "?redirect=" + urlPaypal;
		}

	};

	public abstract String escolhaPagamento(UriComponentsBuilder uriComponentsBuilder, Long id);
}
