package com.oragentalent5.mercadolivre.resources;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oragentalent5.mercadolivre.GatwayPagamento;
import com.oragentalent5.mercadolivre.domain.Compra;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.CompraFormDTO;
import com.oragentalent5.mercadolivre.repositories.CompraRepository;
import com.oragentalent5.mercadolivre.repositories.ProdutoRepository;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private UsuarioRepository UsuarioRepo;
	
	@Autowired
	private CompraRepository compraRepo;

	@Transactional
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CompraFormDTO dto, UriComponentsBuilder uriComponentsBuilder) {

		Produto produtoRequerido = produtoRepo.findById(dto.getIdProduto()).get();

		boolean abateu = produtoRequerido.abateDoEstoque(dto.getQuantidade());
		int quantidade = dto.getQuantidade();
		GatwayPagamento gatwayPag = dto.getGatwayPagamento();

		if (abateu) {
			Usuario user = UsuarioRepo.findByLogin("joaosembraco@gmail.com");
			
			Compra compra = new Compra(produtoRequerido, quantidade, user, dto.getGatwayPagamento());
			compraRepo.save(compra);
			
			System.out.println(compra.getId());

			if (gatwayPag.equals(GatwayPagamento.pagseguro)) {
				String resp = GatwayPagamento.pagseguro.escolhaPagamento(uriComponentsBuilder, compra.getId());
				return ResponseEntity.ok().body(resp);
			} else {
				String resp = GatwayPagamento.paypal.escolhaPagamento(uriComponentsBuilder, compra.getId());
				return ResponseEntity.ok().body(resp);
			}
		}
		return ResponseEntity.badRequest().build();
	}

}
