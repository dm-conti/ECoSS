package it.obiectivo.ecoss.json;

import it.obiectivo.ecoss.domain.Istanzautentecpe;

import java.util.List;

public class cpCustomContrattoResponse extends CustomObjectResponse{
	public cpCustomContrattoResponse() {
	}
	
	/*public List<Istanzautentecpe> getRows() {
		List<Istanzautentecpe> tmpRows = this.rowsAttivi;
		tmpRows.addAll(rowsSospesi);
		tmpRows.addAll(rowsChiusi);
		super.setRows(tmpRows);
		return rowsAttivi;
	}*/
	
	/*public List<Istanzautentecpe> getRowsAttivi() {
		if(rowsAttivi == null){
			Iterator<? extends Object> rowsItr = this.rows.iterator();
			Istanzautentecpe tmpIstanzautentecpe;
			String stato;
			while(rowsItr.hasNext()){
				 tmpIstanzautentecpe = (Istanzautentecpe) rowsItr.next();
				 stato = tmpIstanzautentecpe.getTabellastati().getStato();
				 if(stato.equalsIgnoreCase("Istanza_Attiva")){
					 rowsAttivi.add(tmpIstanzautentecpe);
				 }
			}
			return rowsAttivi;
		}
		return rowsAttivi;
	}*/
	
	public void setRowsAttivi(List<Istanzautentecpe> rows) {
		rowsAttivi = rows;
	}
	
	public List<Istanzautentecpe> getRowsAttivi() {
		return rowsAttivi;
	}
	
	/*public List<Istanzautentecpe> getRowsSospesi() {
		if(rowsSospesi == null){
			Iterator<? extends Object> rowsItr = this.rows.iterator();
			Istanzautentecpe tmpIstanzautentecpe;
			String stato;
			while(rowsItr.hasNext()){
				 tmpIstanzautentecpe = (Istanzautentecpe) rowsItr.next();
				 stato = tmpIstanzautentecpe.getTabellastati().getStato();
				 if(stato.equalsIgnoreCase("Istanza_Sospesa")){
					 rowsSospesi.add(tmpIstanzautentecpe);
				 }
			}
			return rowsSospesi;
		}
		return rowsSospesi;
	}*/
	
	public void setRowsSospesi(List<Istanzautentecpe> rows) {
		this.rowsSospesi = rows;
	}
	
	public List<Istanzautentecpe> getRowsSospesi() {
		return rowsSospesi;
	}
	
	/*public List<Istanzautentecpe> getRowsChiusi() {
		if(rowsChiusi == null){
			Iterator<? extends Object> rowsItr = this.rows.iterator();
			Istanzautentecpe tmpIstanzautentecpe;
			String stato;
			while(rowsItr.hasNext()){
				 tmpIstanzautentecpe = (Istanzautentecpe) rowsItr.next();
				 stato = tmpIstanzautentecpe.getTabellastati().getStato();
				 if(stato.equalsIgnoreCase("Istanza_Chiusa")){
					 rowsChiusi.add(tmpIstanzautentecpe);
				 }
			}
			return rowsChiusi;
		}
		return rowsChiusi;
	}*/
	
	public void setRowsChiusi(List<Istanzautentecpe> rows) {
		this.rowsChiusi = rows;
	}
	
	public List<Istanzautentecpe> getRowsChiusi() {
		return rowsChiusi;
	}
	
	//Un aray che contiene l'ogggetto attuale
	protected List<Istanzautentecpe> rowsAttivi;
	
	//Un aray che contiene l'ogggetto attuale
	protected List<Istanzautentecpe> rowsSospesi;
	
	//Un aray che contiene l'ogggetto attuale
	protected List<Istanzautentecpe> rowsChiusi;
}