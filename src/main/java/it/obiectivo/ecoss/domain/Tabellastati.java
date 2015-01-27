package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "tabellastati", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tabellastati.findAll", query = "SELECT t FROM Tabellastati t"),
    @NamedQuery(name = "Tabellastati.findByIdstato", query = "SELECT t FROM Tabellastati t WHERE t.idstato = :idstato"),
    @NamedQuery(name = "Tabellastati.findByStato", query = "SELECT t FROM Tabellastati t WHERE t.stato = :stato"),
    @NamedQuery(name = "Tabellastati.findByTabella", query = "SELECT t FROM Tabellastati t WHERE t.tabella = :tabella")})
public class Tabellastati implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstato")
    private Integer idstato;
    @Basic(optional = false)
    @Column(name = "stato")
    private String stato;
    @Basic(optional = false)
    @Column(name = "tabella")
    private String tabella;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabellastati")
    private List<Servizilicenzacpe> servizilicenzacpeList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabellastati")
    private List<Istanzautentecpe> istanzautentecpeList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabellastati")
    private List<Licenzautente> licenzautenteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabellastati")
    private List<Licenzacpe> licenzacpeList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabellastati")
    private List<Cpe> cpeList;

    public Tabellastati() {
    }

    public Tabellastati(Integer idstato) {
        this.idstato = idstato;
    }

    public Tabellastati(Integer idstato, String stato, String tabella) {
        this.idstato = idstato;
        this.stato = stato;
        this.tabella = tabella;
    }

    public Integer getIdstato() {
        return idstato;
    }

    public void setIdstato(Integer idstato) {
        this.idstato = idstato;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    
    @JsonBackReference
    public List<Servizilicenzacpe> getServizilicenzacpeList() {
        return servizilicenzacpeList;
    }
    
    public void setServizilicenzacpeList(List<Servizilicenzacpe> servizilicenzacpeList) {
        this.servizilicenzacpeList = servizilicenzacpeList;
    }
    
    @JsonBackReference
    public List<Istanzautentecpe> getIstanzautentecpeList() {
        return istanzautentecpeList;
    }
    
    public void setIstanzautentecpeList(List<Istanzautentecpe> istanzautentecpeList) {
        this.istanzautentecpeList = istanzautentecpeList;
    }
    
    @JsonBackReference
    public List<Licenzautente> getLicenzautenteList() {
        return licenzautenteList;
    }
    
    public void setLicenzautenteList(List<Licenzautente> licenzautenteList) {
        this.licenzautenteList = licenzautenteList;
    }
    
    @JsonBackReference
    public List<Licenzacpe> getLicenzacpeList() {
        return licenzacpeList;
    }
    
    public void setLicenzacpeList(List<Licenzacpe> licenzacpeList) {
        this.licenzacpeList = licenzacpeList;
    }
    
    @JsonBackReference
    public List<Cpe> getCpeList() {
        return cpeList;
    }
    
    public void setCpeList(List<Cpe> cpeList) {
        this.cpeList = cpeList;
    }

    public String getTabella() {
        return tabella;
    }

    public void setTabella(String tabella) {
        this.tabella = tabella;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstato != null ? idstato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabellastati)) {
            return false;
        }
        Tabellastati other = (Tabellastati) object;
        if ((this.idstato == null && other.idstato != null) || (this.idstato != null && !this.idstato.equals(other.idstato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Tabellastati[idstato=" + idstato + "]";
    }

}