package com.naveenmereddi.models.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.util.SpecificSearchCriteria;

public class TaskSpecification implements Specification<Task> {

	private SpecificSearchCriteria criteria;

	public TaskSpecification(SpecificSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		switch(criteria.getOperator()) {
		case NEGATION:
			return cb.notEqual(root.get(criteria.getKey()), criteria.getValue());
		default:
			return null;
		}
	}

}
