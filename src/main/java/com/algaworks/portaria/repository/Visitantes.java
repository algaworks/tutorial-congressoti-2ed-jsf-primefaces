package com.algaworks.portaria.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.portaria.model.Visitante;

public class Visitantes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Visitante guardar(Visitante visitante) {
		manager.getTransaction().begin();
		visitante = manager.merge(visitante);
		manager.getTransaction().commit();
		
		return visitante;
	}

	public List<Visitante> todos() {
		return manager.createQuery("from Visitante order by dataVisita desc",
				Visitante.class).getResultList();
	}

	public Visitante porId(Long id) {
		return manager.find(Visitante.class, id);
	}
	
}
