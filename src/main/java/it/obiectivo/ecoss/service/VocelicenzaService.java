package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Vocelicenza;
import java.util.List;

public interface VocelicenzaService {
	public List<Vocelicenza> getAll();
	public Vocelicenza get(Integer id);
	public Vocelicenza getByName(String nomeLicenza);
	public void add(Vocelicenza vocelicenza);
	public void delete(Integer id);
	public void edit(Vocelicenza vocelicenza);
}
