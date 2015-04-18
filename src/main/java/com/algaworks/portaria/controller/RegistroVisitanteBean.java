package com.algaworks.portaria.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.algaworks.portaria.model.Visitante;
import com.algaworks.portaria.repository.Visitantes;

@Named
@ViewScoped
public class RegistroVisitanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FotoCameraBean fotoCamera;
	
	@Inject
	private Visitantes visitantes;
	
	private Visitante visitante;
	
	private List<Visitante> todosVisitantes;

	public void consultar() {
		todosVisitantes = visitantes.todos();
	}
	
	public void novo() {
		visitante = new Visitante();
		fotoCamera.limpar();
	}
	
	public void adicionar() {
		visitante.setDataVisita(new Date());
		visitante.setFoto(fotoCamera.getFotoBytes());
		
		visitantes.guardar(visitante);
		
		consultar();
		
		FacesMessage msg = new FacesMessage("Visitante registrado!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		RequestContext.getCurrentInstance().execute("PF('novoVisitanteDialog').hide()");
		RequestContext.getCurrentInstance().update("frm:msgs-geral");
	}
	
	public Visitante getVisitante() {
		return visitante;
	}

	public List<Visitante> getTodosVisitantes() {
		return todosVisitantes;
	}
	
}