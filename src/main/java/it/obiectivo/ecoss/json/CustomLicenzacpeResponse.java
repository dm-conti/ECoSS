package it.obiectivo.ecoss.json;

public class CustomLicenzacpeResponse extends CustomObjectResponse{
	
	public CustomLicenzacpeResponse() {
	}
	
	public CustomLicenzacpeResponse(String nomeLicenza) {
		this.licNome = nomeLicenza;
	}
		
	public String getLicNome() {
		return licNome;
	}

	public void setLicNome(String licNome) {
		this.licNome = licNome;
	}
	
	//Nome Simbolico della Licenza
	private String licNome;
}