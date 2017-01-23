package io.egen.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Role;
import io.egen.api.repository.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Role> findAll() {
		TypedQuery<Role> query = entityManager.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public Role findOne(String id) {
		return entityManager.find(Role.class, id);
	}

	@Override
	public Role findByType(String type) {
		return entityManager.find(Role.class, type);
	}

	@Override
	public Role create(Role role) {
		entityManager.persist(role);
		return role;
	}

}
