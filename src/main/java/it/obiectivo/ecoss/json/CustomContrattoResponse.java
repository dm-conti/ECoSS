package it.obiectivo.ecoss.json;

public class CustomContrattoResponse extends CustomObjectResponse{
	public CustomContrattoResponse() {
	}
	
	public void setRowsAttivi(Integer numOf) {
		rowsAttivi = numOf;
	}
	
	public Integer getRowsAttivi() {
		return rowsAttivi;
	}
	
	public void setRowsSospesi(Integer numOf) {
		this.rowsSospesi = numOf;
	}
	
	public Integer getRowsSospesi() {
		return rowsSospesi;
	}
	
	public void setRowsChiusi(Integer numOf) {
		this.rowsChiusi = numOf;
	}
	
	public Integer getRowsChiusi() {
		return rowsChiusi;
	}
	
	private Integer rowsAttivi;
	
	private Integer rowsSospesi;
	
	private Integer rowsChiusi;
}