package com.oragentalent5.mercadolivre.resources;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Compra;
import com.oragentalent5.mercadolivre.domain.EventoCompraSucesso;
import com.oragentalent5.mercadolivre.dto.RetornoPagSeguroDTO;
import com.oragentalent5.mercadolivre.dto.RetornoPayPalDTO;
import com.oragentalent5.mercadolivre.email.EnvioEmailNotaFiscalWork;
import com.oragentalent5.mercadolivre.repositories.CompraRepository;

@RestController
@RequestMapping("/")
public class FinalizaCompraController {

	@Autowired
	private CompraRepository compraRepo;
	@Autowired
	private Set<EventoCompraSucesso> ecventosComprasSucesso;

	@Autowired
	private EnvioEmailNotaFiscalWork emailWork;
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	public ResponseEntity<?> processoPagSeguro(@PathVariable("id") Long id, @Valid @RequestBody RetornoPagSeguroDTO dto) {

		Compra compra = compraRepo.findById(id).get();
		compra.addTransacao(dto);
		compraRepo.save(compra);
		
		if (compra.processadaComSucesso()) {
			ecventosComprasSucesso.stream().forEach(e -> e.processa(compra));
			emailWork.emailConfirmacao(compra);
		} else {
			emailWork.falhou(compra);
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	public ResponseEntity<?> processoPaypalSeguro(@PathVariable("id") Long id, @Valid @RequestBody RetornoPayPalDTO dto) {

		Compra compra = compraRepo.findById(id).get();
		compra.addTransacao(dto);
		compraRepo.save(compra);
		
		return ResponseEntity.ok().build();
	}
}
