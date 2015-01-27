package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "licenzautente", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Licenzautente.findAll", query = "SELECT l FROM Licenzautente l"),
    @NamedQuery(name = "Licenzautente.findByIdLicenzaUtente", query = "SELECT l FROM Licenzautente l WHERE l.idLicenzaUtente = :idLicenzaUtente"),
    @NamedQuery(name = "Licenzautente.findByLicUteDescrizione", query = "SELECT l FROM Licenzautente l WHERE l.licUteDescrizione = :licUteDescrizione"),
    @NamedQuery(name = "Licenzautente.findByLicUteDataAttivazione", query = "SELECT l FROM Licenzautente l WHERE l.dataAttivazione = :dataAttivazione"),
    @NamedQuery(name = "Licenzautente.findByLicUteDataCessazione", query = "SELECT l FROM Licenzautente l WHERE l.dataCessazione = :dataCessazione"),
    @NamedQuery(name = "Licenzautente.findByStato", query = "SELECT l FROM Licenzautente l WHERE l.tabellastati.stato = :stato")})
public class Licenzautente extends CheckDate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLicenzaUtente")
    private Integer idLicenzaUtente;
    
    @Basic(optional = false)
    @Column(name = "licUteDescrizione")
    private String licUteDescrizione;
    
    @Basic(optional = false)
    @Column(name = "licUteDataAttivazione")
    @Temporal(TemporalType.DATE)
    private Date dataAttivazione;
    
    @Basic(optional = false)
    @Column(name = "licUteDataCessazione")
    @Temporal(TemporalType.DATE)
    private Date dataCessazione;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenzautente")
    private List<Istanzautentecpe> istanzautentecpeList;
    
    @JoinColumn(name = "licenzeCommerciali_idLicenzeCommerciali", referencedColumnName = "idLicenzeCommerciali")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Licenzecommerciali licenzecommerciali;
    
    @JoinColumn(name = "stato", referencedColumnName = "idstato")
    @ManyToOne(optional = false)
    private Tabellastati tabellastati;

    public Licenzautente() {
    }

    public Licenzautente(Integer idLicenzaUtente) {
        this.idLicenzaUtente = idLicenzaUtente;
    }

    public Licenzautente(Integer idLicenzaUtente, String licUteDescrizione, Date licUteDataAttivazione, Date licUteDataCessazione) {
        this.idLicenzaUtente = idLicenzaUtente;
        this.licUteDescrizione = licUteDescrizione;
        this.dataAttivazione = licUteDataAttivazione;
        this.dataCessazione = licUteDataCessazione;
    }

    public Integer getId() {
        return idLicenzaUtente;
    }

    public void setId(Integer idLicenzaUtente) {
        this.idLicenzaUtente = idLicenzaUtente;
    }

    public String getLicUteDescrizione() {
        return licUteDescrizione;
    }

    public void setLicUteDescrizione(String licUteDescrizione) {
        this.licUteDescrizione = licUteDescrizione;
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
    
    @JsonBackReference
    public List<Istanzautentecpe> getIstanzautentecpeList() {
        return istanzautentecpeList;
    }
    
    public void setIstanzautentecpeList(List<Istanzautentecpe> istanzautentecpeList) {
        this.istanzautentecpeList = istanzautentecpeList;
    }

    @JsonManagedReference
    public Licenzecommerciali getLicenzecommerciali() {
        return licenzecommerciali;
    }
    
    public void setLicenzecommerciali(Licenzecommerciali licenzecommerciali) {
        this.licenzecommerciali = licenzecommerciali;
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
        hash += (idLicenzaUtente != null ? idLicenzaUtente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licenzautente)) {
            return false;
        }
        Licenzautente other = (Licenzautente) object;
        if ((this.idLicenzaUtente == null && other.idLicenzaUtente != null) || (this.idLicenzaUtente != null && !this.idLicenzaUtente.equals(other.idLicenzaUtente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Licenzautente[idLicenzaUtente=" + idLicenzaUtente + "]";
    }
}