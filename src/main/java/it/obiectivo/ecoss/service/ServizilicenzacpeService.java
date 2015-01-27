package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Servizilicenzacpe;

import java.util.List;

public interface ServizilicenzacpeService {
	public List<Servizilicenzacpe> getAll();
	public List<Servizilicenzacpe> getByStato(String stato);
	public Servizilicenzacpe get(Integer id);
	public Servizilicenzacpe getByIdServiziLicenzaCPE(Integer idServiziLiceCpe);
	public Boolean add(Servizilicenzacpe servizio);
	public Boolean delete(Integer id);
	public Boolean edit(Servizilicenzacpe servizio);	
}