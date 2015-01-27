package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "licenzacpe", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Licenzacpe.findAll", query = "SELECT l FROM Licenzacpe l"),
    @NamedQuery(name = "Licenzacpe.findByIdLicenzaCPE", query = "SELECT l FROM Licenzacpe l WHERE l.idLicenzaCPE = :idLicenzaCPE"),
    @NamedQuery(name = "Licenzacpe.findByLicCpeNome", query = "SELECT l FROM Licenzacpe l WHERE l.licCpeNome = :licCpeNome"),
    @NamedQuery(name = "Licenzacpe.findByLicCpeDataAttivazione", query = "SELECT l FROM Licenzacpe l WHERE l.dataAttivazione = :dataAttivazione"),
    @NamedQuery(name = "Licenzacpe.findByLicCpeDataCessazione", query = "SELECT l FROM Licenzacpe l WHERE l.dataCessazione = :dataCessazione"),
    @NamedQuery(name = "Licenzacpe.findByLicCpeRegistrationKey", query = "SELECT l FROM Licenzacpe l WHERE l.licCpeRegistrationKey = :licCpeRegistrationKey"),
    @NamedQuery(name = "Licenzacpe.findByStato", query = "SELECT l FROM Licenzacpe l WHERE l.tabellastati.stato = :stato")})
public class Licenzacpe extends CheckDate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLicenzaCPE")
    private Integer idLicenzaCPE;
    
    @Basic(optional = false)
    @Column(name = "licCpeNome")
    private String licCpeNome;
    
    @Basic(optional = false)
    @Column(name = "licCpeDataAttivazione")
    @Temporal(TemporalType.DATE)
    private Date dataAttivazione;
    
    @Basic(optional = false)
    @Column(name = "licCpeDataCessazione")
    @Temporal(TemporalType.DATE)
    private Date dataCessazione;
    
    @Basic(optional = false)
    @Column(name = "licCpeRegistrationKey")
    private String licCpeRegistrationKey;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenzacpe")
    private List<Servizilicenzacpe> servizilicenzacpeList;
    
    @JoinColumn(name = "cpe_IdCpe", referencedColumnName = "idCPE")
    @ManyToOne(optional = false)
    private Cpe cpe;
    
    @JoinColumn(name = "stato", referencedColumnName = "idstato")
    @ManyToOne(optional = false)
    private Tabellastati tabellastati;

    public Licenzacpe() {
    }

    public Licenzacpe(Integer idLicenzaCPE) {
        this.idLicenzaCPE = idLicenzaCPE;
    }

    public Licenzacpe(Integer idLicenzaCPE, String licCpeNome, Date licCpeDataAttivazione, Date licCpeDataCessazione, String licCpeRegistrationKey) {
        this.idLicenzaCPE = idLicenzaCPE;
        this.licCpeNome = licCpeNome;
        this.dataAttivazione = licCpeDataAttivazione;
        this.dataCessazione = licCpeDataCessazione;
        this.licCpeRegistrationKey = licCpeRegistrationKey;
    }

    public Integer getId() {
        return idLicenzaCPE;
    }

    public void setId(Integer idLicenzaCPE) {
        this.idLicenzaCPE = idLicenzaCPE;
    }

    public String getLicCpeNome() {
        return licCpeNome;
    }

    public void setLicCpeNome(String licCpeNome) {
        this.licCpeNome = licCpeNome;
    }

    public Date getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(Date licCpeDataAttivazione) {
        this.dataAttivazione = licCpeDataAttivazione;
    }

    public Date getDataCessazione() {
        return dataCessazione;
    }

    public void setDataCessazione(Date licCpeDataCessazione) {
        this.dataCessazione = licCpeDataCessazione;
    }

    public String getLicCpeRegistrationKey() {
        return licCpeRegistrationKey;
    }

    public void setLicCpeRegistrationKey(String licCpeRegistrationKey) {
        this.licCpeRegistrationKey = licCpeRegistrationKey;
    }
    
    @JsonBackReference
    public List<Servizilicenzacpe> getServizilicenzacpeList() {
        return servizilicenzacpeList;
    }
    
    public void setServizilicenzacpeList(List<Servizilicenzacpe> servizilicenzacpeList) {
        this.servizilicenzacpeList = servizilicenzacpeList;
    }
    
    @JsonManagedReference
    public Cpe getCpe() {
        return cpe;
    }
    
    public void setCpe(Cpe cpe) {
        this.cpe = cpe;
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
        hash += (idLicenzaCPE != null ? idLicenzaCPE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licenzacpe)) {
            return false;
        }
        Licenzacpe other = (Licenzacpe) object;
        if ((this.idLicenzaCPE == null && other.idLicenzaCPE != null) || (this.idLicenzaCPE != null && !this.idLicenzaCPE.equals(other.idLicenzaCPE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Licenzacpe[idLicenzaCPE=" + idLicenzaCPE + "]";
    }

}