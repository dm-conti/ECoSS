package it.obiectivo.ecoss.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Un oggetto POJO custom che verra' convertito, automaticamente, nel formato JSON. 
 * <p>Questo viene usato per inviare messaggi generici alla nostra JqGrid, 
 * quando una richiesta ha avuto successo o meno.
 * Naturalmente, sarà necessario utilizzare JavaScript per analizzare la risposta JSON. 
 */
public class CustomGenericResponse {

	//true per successful
	private Boolean success;
	
	//Un generico messaggio: es. 'La tua richiesta e' stata processata!'
	private List<String> message;
	
	public CustomGenericResponse() {
		message = new ArrayList<String>();
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public List<String> getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message.add(message);
	}
}