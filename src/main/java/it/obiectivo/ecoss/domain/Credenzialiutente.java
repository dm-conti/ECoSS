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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "credenzialiutente", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Credenzialiutente.findAll", query = "SELECT c FROM Credenzialiutente c"),
    @NamedQuery(name = "Credenzialiutente.findByIdCredenzialiUtente", query = "SELECT c FROM Credenzialiutente c WHERE c.idCredenzialiUtente = :idCredenzialiUtente"),
    @NamedQuery(name = "Credenzialiutente.findByUserId", query = "SELECT c FROM Credenzialiutente c WHERE c.userId = :userId"),
    @NamedQuery(name = "Credenzialiutente.findByPassword", query = "SELECT c FROM Credenzialiutente c WHERE c.password = :password"),
    @NamedQuery(name = "Credenzialiutente.findByDataInserimento", query = "SELECT c FROM Credenzialiutente c WHERE c.dataInserimento = :dataInserimento"),
    @NamedQuery(name = "Credenzialiutente.findByPrimoAccesso", query = "SELECT c FROM Credenzialiutente c WHERE c.primoAccesso = :primoAccesso")})
public class Credenzialiutente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCredenzialiUtente")
    private Integer idCredenzialiUtente;
    
    @Basic(optional = false)
    @Column(name = "userId")
    private String userId;
    
    @Column(name = "password")
    private String password;
    
    @Basic(optional = false)
    @Column(name = "dataInserimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInserimento;
    
    @Column(name = "primoAccesso")
    private Boolean primoAccesso;
    
    @JoinColumn(name = "profilo_idProfilo", referencedColumnName = "idProfilo")
    @ManyToOne(optional = false)
    private Profilo profilo;
    
    @OneToOne(mappedBy = "credenzialiutente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Gestore gestore;
    
    @OneToOne(mappedBy = "credenzialiutente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Distributore distributore;
    
    @OneToOne(mappedBy = "credenzialiutente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Utente utente;

    public Credenzialiutente() {
    }

    public Credenzialiutente(Integer idCredenzialiUtente) {
        this.idCredenzialiUtente = idCredenzialiUtente;
    }

    public Credenzialiutente(Integer idCredenzialiUtente, String userId, Date dataInserimento) {
        this.idCredenzialiUtente = idCredenzialiUtente;
        this.userId = userId;
        this.dataInserimento = dataInserimento;
    }

    public Integer getIdCredenzialiUtente() {
        return idCredenzialiUtente;
    }

    public void setIdCredenzialiUtente(Integer idCredenzialiUtente) {
        this.idCredenzialiUtente = idCredenzialiUtente;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Boolean getPrimoAccesso() {
        return primoAccesso;
    }

    public void setPrimoAccesso(Boolean primoAccesso) {
        this.primoAccesso = primoAccesso;
    }
    
    @JsonBackReference
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
    @JsonManagedReference
    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
    }
    
    @JsonBackReference
    public Gestore getGestore() {
        return gestore;
    }

    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
    }
    
    @JsonBackReference
    public Distributore getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCredenzialiUtente != null ? idCredenzialiUtente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credenzialiutente)) {
            return false;
        }
        Credenzialiutente other = (Credenzialiutente) object;
        if ((this.idCredenzialiUtente == null && other.idCredenzialiUtente != null) || (this.idCredenzialiUtente != null && !this.idCredenzialiUtente.equals(other.idCredenzialiUtente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Credenzialiutente[idCredenzialiUtente=" + idCredenzialiUtente + "]";
    }

}