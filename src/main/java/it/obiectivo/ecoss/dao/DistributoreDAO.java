package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Distributore;
import java.util.List;

public interface DistributoreDAO {
	public List<Distributore> getAll();
	public Distributore get(Integer id);
	public Distributore getByRagSociale(String ragioneSociale);
	public void add(Distributore distributore);
	public void delete(Integer id);
	public void edit(Distributore distributore);
}