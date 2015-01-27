package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "servizilicenzacpe", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Servizilicenzacpe.findAll", query = "SELECT s FROM Servizilicenzacpe s"),
    @NamedQuery(name = "Servizilicenzacpe.findByIdServiziLicenzaCPE", query = "SELECT s FROM Servizilicenzacpe s WHERE s.idServiziLicenzaCPE = :idServiziLicenzaCPE"),
    @NamedQuery(name = "Servizilicenzacpe.findByServDataInizio", query = "SELECT s FROM Servizilicenzacpe s WHERE s.dataAttivazione = :dataAttivazione"),
    @NamedQuery(name = "Servizilicenzacpe.findByServScadenza", query = "SELECT s FROM Servizilicenzacpe s WHERE s.dataCessazione = :dataCessazione"),
    @NamedQuery(name = "Servizilicenzacpe.findByStato", query = "SELECT s FROM Servizilicenzacpe s WHERE s.tabellastati.stato = :stato")})
public class Servizilicenzacpe extends CheckDate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServiziLicenzaCPE")
    private Integer idServiziLicenzaCPE;
    
    @Basic(optional = false)
    @Column(name = "servDataInizio")
    @Temporal(TemporalType.DATE)
    private Date dataAttivazione;
    
    @Basic(optional = false)
    @Column(name = "servScadenza")
    @Temporal(TemporalType.DATE)
    private Date dataCessazione;
    
    @JoinColumn(name = "licenzaCPE_idLicenzaCPE", referencedColumnName = "idLicenzaCPE")
    @ManyToOne(optional = false)
    private Licenzacpe licenzacpe;
    
    @JoinColumn(name = "voceLicenza_idVoceLicenza", referencedColumnName = "idVoceLicenza")
    @ManyToOne(optional = false)
    private Vocelicenza vocelicenza;
    
    @JoinColumn(name = "stato", referencedColumnName = "idstato")
    @ManyToOne(optional = false)
    private Tabellastati tabellastati;

    public Servizilicenzacpe() {
    }

    public Servizilicenzacpe(Integer idServiziLicenzaCPE) {
        this.idServiziLicenzaCPE = idServiziLicenzaCPE;
    }

    public Servizilicenzacpe(Integer idServiziLicenzaCPE, Date servDataInizio, Date servScadenza) {
        this.idServiziLicenzaCPE = idServiziLicenzaCPE;
        this.dataAttivazione = servDataInizio;
        this.dataCessazione = servScadenza;
    }

    public Integer getId() {
        return idServiziLicenzaCPE;
    }

    public void setId(Integer idServiziLicenzaCPE) {
        this.idServiziLicenzaCPE = idServiziLicenzaCPE;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(Date servDataInizio) {
        this.dataAttivazione = servDataInizio;
    }

    public Date getDataCessazione() {
        return dataCessazione;
    }

    public void setDataCessazione(Date servScadenza) {
        this.dataCessazione = servScadenza;
    }
    
    @JsonManagedReference
    public Licenzacpe getLicenzacpe() {
        return licenzacpe;
    }
    
    public void setLicenzacpe(Licenzacpe licenzacpe) {
        this.licenzacpe = licenzacpe;
    }
    
    @JsonManagedReference
    public Vocelicenza getVocelicenza() {
        return vocelicenza;
    }
    
    public void setVocelicenza(Vocelicenza vocelicenza) {
        this.vocelicenza = vocelicenza;
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
        hash += (idServiziLicenzaCPE != null ? idServiziLicenzaCPE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servizilicenzacpe)) {
            return false;
        }
        Servizilicenzacpe other = (Servizilicenzacpe) object;
        if ((this.idServiziLicenzaCPE == null && other.idServiziLicenzaCPE != null) || (this.idServiziLicenzaCPE != null && !this.idServiziLicenzaCPE.equals(other.idServiziLicenzaCPE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Servizilicenzacpe[idServiziLicenzaCPE=" + idServiziLicenzaCPE + "]";
    }

}