package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "istanzautentecpe", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Istanzautentecpe.findAll", query = "SELECT i FROM Istanzautentecpe i"),
    @NamedQuery(name = "Istanzautentecpe.findByIdIstanzaUtenteCPE", query = "SELECT i FROM Istanzautentecpe i WHERE i.idIstanzaUtenteCPE = :idIstanzaUtenteCPE"),
    @NamedQuery(name = "Istanzautentecpe.findByDataAttivazione", query = "SELECT i FROM Istanzautentecpe i WHERE i.dataAttivazione = :dataAttivazione"),
    @NamedQuery(name = "Istanzautentecpe.findByDataCessazione", query = "SELECT i FROM Istanzautentecpe i WHERE i.dataCessazione = :dataCessazione"),
    @NamedQuery(name = "Istanzautentecpe.findByNumeroContratto", query = "SELECT i FROM Istanzautentecpe i WHERE i.numeroContratto = :numeroContratto"),
    @NamedQuery(name = "Istanzautentecpe.findByStato", query = "SELECT i FROM Istanzautentecpe i WHERE i.tabellastati.stato = :stato")})
public class Istanzautentecpe extends CheckDate implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIstanzaUtenteCPE")
    private Integer idIstanzaUtenteCPE;
	
	@Basic(optional = false)
    @Column(name = "dataInizio")
    @Temporal(TemporalType.DATE)
    private Date dataAttivazione;
	
    @Basic(optional = false)
    @Column(name = "dataFine")
    @Temporal(TemporalType.DATE)
    private Date dataCessazione;
    
    @Basic(optional = false)
    @Column(name = "numeroContratto")
    private String numeroContratto;
    
    @JoinColumn(name = "cpe_idCPE", referencedColumnName = "idCPE")
    @ManyToOne(optional = false)
    private Cpe cpe;
    
    @JoinColumn(name = "utente_idUtente", referencedColumnName = "idUtente")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Utente utente;
    
    @JoinColumn(name = "licenzautente_idLicenzaUtente", referencedColumnName = "idLicenzaUtente")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Licenzautente licenzautente;
    
    @JoinColumn(name = "stato", referencedColumnName = "idstato")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Tabellastati tabellastati;

    public Istanzautentecpe() {
    }

    public Istanzautentecpe(Integer idIstanzaUtenteCPE) {
        this.idIstanzaUtenteCPE = idIstanzaUtenteCPE;
    }

    public Istanzautentecpe(Integer idIstanzaUtenteCPE, Date dataInizio, Date dataFine, String numeroContratto) {
        this.idIstanzaUtenteCPE = idIstanzaUtenteCPE;
        //this.dataAttivazione = dataInizio;
        //this.dataCessazione = dataFine;
        this.dataAttivazione = dataInizio;
        this.dataCessazione = dataFine;
        this.numeroContratto = numeroContratto;
    }

    public Integer getId() {
        return idIstanzaUtenteCPE;
    }

    public void setId(Integer idIstanzaUtenteCPE) {
        this.idIstanzaUtenteCPE = idIstanzaUtenteCPE;
    }
    
    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(Date licUteDataAttivazione) {
        this.dataAttivazione = licUteDataAttivazione;
    }

    public Date getDataCessazione() {
        return dataCessazione;
    }

    public void setDataCessazione(Date licUteDataCessazione) {
        this.dataCessazione = licUteDataCessazione;
    }

    public String getNumeroContratto() {
        return numeroContratto;
    }

    public void setNumeroContratto(String numeroContratto) {
        this.numeroContratto = numeroContratto;
    }
    
    @JsonManagedReference
    public Cpe getCpe() {
        return cpe;
    }
    
    public void setCpe(Cpe cpe) {
        this.cpe = cpe;
    }
    
    @JsonManagedReference
    public Utente getUtente() {
        return utente;
    }
    
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
    @JsonManagedReference
    public Licenzautente getLicenzautente() {
        return licenzautente;
    }
    
    public void setLicenzautente(Licenzautente licenzautente) {
        this.licenzautente = licenzautente;
    }
    
    @JsonManagedReference
    public Tabellastati getTabellastati() {
        return tabellastati;
    }
    
    public void setTabellastati(Tabellastati tabellastati) {
        this.tabellastati = tabellastati;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIstanzaUtenteCPE != null ? idIstanzaUtenteCPE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Istanzautentecpe)) {
            return false;
        }
        Istanzautentecpe other = (Istanzautentecpe) object;
        if ((this.idIstanzaUtenteCPE == null && other.idIstanzaUtenteCPE != null) || (this.idIstanzaUtenteCPE != null && !this.idIstanzaUtenteCPE.equals(other.idIstanzaUtenteCPE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Istanzautentecpe[idIstanzaUtenteCPE=" + idIstanzaUtenteCPE + "]";
    }

}
