package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Servizilicenzacpe;
import it.obiectivo.ecoss.domain.Tabellastati;

import java.util.Date;

/* QUESTA CLASSE NON DEVE CONSIDERARSI COME UN VERO E PROPIO DAO 
 * MA UN DAO VIRTUALE USATO PER VIRTUALIZZARE IN UN UNICO OGGETTO
 * DATI PROVENIENTI DA DUE DIVERSE ENTITIES */

public class ServizioDAO {
	
	private int idVoceLicenza;
	private String nomeServizio;
	private Date dataInizio;
	private Date dataFine;
	private Tabellastati stato;
	
	public ServizioDAO(){}
	
	public ServizioDAO(Servizilicenzacpe servizioLicCpe){
		this.idVoceLicenza = servizioLicCpe.getVocelicenza().getIdVoceLicenza();
		this.nomeServizio = servizioLicCpe.getVocelicenza().getVoceLicenza();
		this.dataInizio = servizioLicCpe.getDataAttivazione();
		this.dataFine = servizioLicCpe.getDataCessazione();
		this.stato = servizioLicCpe.getTabellastati();
	}

	public int getIdVoceLicenza() {
		return idVoceLicenza;
	}

	public void setIdVoceLicenza(int idVoceLicenza) {
		this.idVoceLicenza = idVoceLicenza;
	}
	
	public String getNomeServizio() {
		return nomeServizio;
	}

	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Tabellastati getTabellastati() {
		return stato;
	}

	public void setTabellastati(Tabellastati stato) {
		
		this.stato = stato;
	}
}