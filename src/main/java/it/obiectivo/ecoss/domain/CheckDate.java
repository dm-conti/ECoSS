package it.obiectivo.ecoss.domain;

import java.util.Date;

public abstract class CheckDate {
	public abstract Integer getId();
	public abstract Date getDataAttivazione();
	public abstract Date getDataCessazione();
	public abstract Tabellastati getTabellastati();
	public abstract void setTabellastati(Tabellastati tabellastati);
}
