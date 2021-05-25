package com.oragentalent5.mercadolivre.resources.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	private String domainAttribute;
	private Class<?> klass;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(UniqueValue ann) {
		domainAttribute = ann.fieldName();
		klass = ann.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");

		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, domainAttribute + "Já existente");

		return list.isEmpty();
	}
}
