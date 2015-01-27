package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Profilo;
import java.util.List;

public interface ProfiloService {
	public List<Profilo> getAll();
	public Profilo get(Integer id);
	public Profilo getByProfDescrizione(String descrizione);
	public Boolean add(Profilo profilo);
	public Boolean delete(Integer id);
	public Boolean edit(Profilo profilo);
}