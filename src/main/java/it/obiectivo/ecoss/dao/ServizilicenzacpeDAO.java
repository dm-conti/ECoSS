package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Servizilicenzacpe;

import java.util.List;

public interface ServizilicenzacpeDAO {
	public List<Servizilicenzacpe> getAll();
	public List<Servizilicenzacpe> getByStato(String stato);
	public Servizilicenzacpe get(Integer id);
	public Servizilicenzacpe getByIdServiziLicenzaCPE(Integer idServiziLiceCpe);
	public void add(Servizilicenzacpe servizi);
	public void delete(Integer id);
	public void edit(Servizilicenzacpe servizi);
}