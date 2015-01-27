package it.obiectivo.ecoss.json;

import java.util.List;

public class CustomObjectResponse {

	//Pagina corrente della query
	protected String page;
	
	//Pagine totali della query
	protected String total;
	
	//Numero totale di record
	protected String records;
	
	//Un aray che contiene l'ogggetto attuale
	protected List<? extends Object> rows;

	public CustomObjectResponse() {
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<? extends Object> getRows() {
		return rows;
	}

	public void setRows(List<? extends Object> rows) {
		this.rows = rows;
	}
}