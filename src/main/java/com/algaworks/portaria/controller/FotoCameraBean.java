package com.algaworks.portaria.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class FotoCameraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent fotoContent;
	private byte[] fotoBytes;
	
	public void limpar() {
		fotoContent = null;
		fotoBytes = null;
	}
	
	public void aoCapturarFoto(CaptureEvent event) {
		fotoBytes = event.getData();
		fotoContent = new ByteArrayContent(fotoBytes, "image/jpeg");
	}

	public StreamedContent getFotoContent() {
		return fotoContent;
	}

	public byte[] getFotoBytes() {
		return fotoBytes;
	}
	
	public boolean isFotoCapturada() {
		return getFotoContent() != null;
	}
	
}