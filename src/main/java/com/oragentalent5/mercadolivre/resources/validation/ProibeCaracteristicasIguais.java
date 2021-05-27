package com.oragentalent5.mercadolivre.resources.validation;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.oragentalent5.mercadolivre.dto.ProdutoFormDTO;

public class ProibeCaracteristicasIguais implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return ProdutoFormDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		ProdutoFormDTO req = (ProdutoFormDTO) target;
		Set<String> nomesIguais = req.buscarCaracteristicaIgual();
		if (!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas",null, "Já tem essa característica: " + nomesIguais);
		}
//		List<CaracteristicasFormDTO> caracteristicas = req.getCaracteristicas();
//		Set<String> contem = new HashSet<>();
//		caracteristicas.stream().forEach(c -> {
//			if (contem.contains(c.getNome())) {
//				errors.rejectValue("caracteristicas",null, "Já tem essa caracteristica, Nome: " + c.getNome() + 
//						" e " + c.getDescricao());
//			}
//			contem.add(c.getNome());
//		});

	}
}
