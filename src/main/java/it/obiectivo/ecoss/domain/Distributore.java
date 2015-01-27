/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "distributore", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Distributore.findAll", query = "SELECT d FROM Distributore d"),
    @NamedQuery(name = "Distributore.findByIdDistributore", query = "SELECT d FROM Distributore d WHERE d.idDistributore = :idDistributore"),
    @NamedQuery(name = "Distributore.findByDistRagSociale", query = "SELECT d FROM Distributore d WHERE d.distRagSociale = :distRagSociale"),
    @NamedQuery(name = "Distributore.findByDistDescrizione", query = "SELECT d FROM Distributore d WHERE d.distDescrizione = :distDescrizione"),
    @NamedQuery(name = "Distributore.findByDistIndirizzo", query = "SELECT d FROM Distributore d WHERE d.distIndirizzo = :distIndirizzo"),
    @NamedQuery(name = "Distributore.findByDistCitta", query = "SELECT d FROM Distributore d WHERE d.distCitta = :distCitta"),
    @NamedQuery(name = "Distributore.findByDistCap", query = "SELECT d FROM Distributore d WHERE d.distCap = :distCap"),
    @NamedQuery(name = "Distributore.findByDistTel1", query = "SELECT d FROM Distributore d WHERE d.distTel1 = :distTel1"),
    @NamedQuery(name = "Distributore.findByDistTel2", query = "SELECT d FROM Distributore d WHERE d.distTel2 = :distTel2"),
    @NamedQuery(name = "Distributore.findByDistFax", query = "SELECT d FROM Distributore d WHERE d.distFax = :distFax"),
    @NamedQuery(name = "Distributore.findByDistEmail", query = "SELECT d FROM Distributore d WHERE d.distEmail = :distEmail"),
    @NamedQuery(name = "Distributore.findByDistDataCreazione", query = "SELECT d FROM Distributore d WHERE d.distDataCreazione = :distDataCreazione"),
    @NamedQuery(name = "Distributore.findByDistDataCessazione", query = "SELECT d FROM Distributore d WHERE d.distDataCessazione = :distDataCessazione"),
    @NamedQuery(name = "Distributore.findByDistCF", query = "SELECT d FROM Distributore d WHERE d.distCF = :distCF"),
    @NamedQuery(name = "Distributore.findByDistEmail2", query = "SELECT d FROM Distributore d WHERE d.distEmail2 = :distEmail2"),
    @NamedQuery(name = "Distributore.findByDistPIVA", query = "SELECT d FROM Distributore d WHERE d.distPIVA = :distPIVA")})
public class Distributore implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDistributore")
    private Integer idDistributore;
    
    @Column(name = "distRagSociale")
    private String distRagSociale;
    
    @Column(name = "distDescrizione")
    private String distDescrizione;
    
    @Column(name = "distIndirizzo")
    private String distIndirizzo;
    
    @Column(name = "distCitta")
    private String distCitta;
    
    @Column(name = "distCap")
    private String distCap;
    
    @Column(name = "distTel1")
    private String distTel1;
    
    @Column(name = "distTel2")
    private String distTel2;
    
    @Column(name = "distFax")
    private String distFax;
    
    @Column(name = "distEmail")
    private String distEmail;
    
    @Basic(optional = false)
    @Column(name = "distDataCreazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date distDataCreazione;
    
    @Column(name = "distDataCessazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date distDataCessazione;
    
    @Column(name = "distCF")
    private String distCF;
    
    @Column(name = "distEmail2")
    private String distEmail2;
    
    @Column(name = "distPIVA")
    private String distPIVA;
    
    @OneToMany(mappedBy = "distributore")
    private List<Utente> utenteList;
    
    @JoinColumn(name = "credenziali_idCredenziali", referencedColumnName = "IdCredenzialiUtente")
    @OneToOne
    private Credenzialiutente credenzialiutente;
    
    @JoinColumn(name = "gestore_idGestore", referencedColumnName = "idGestore")
    @ManyToOne(optional = false)
    private Gestore gestore;

    public Distributore() {
    }

    public Distributore(Integer idDistributore) {
        this.idDistributore = idDistributore;
    }

    public Distributore(Integer idDistributore, Date distDataCreazione) {
        this.idDistributore = idDistributore;
        this.distDataCreazione = distDataCreazione;
    }

    public Integer getIdDistributore() {
        return idDistributore;
    }

    public void setIdDistributore(Integer idDistributore) {
        this.idDistributore = idDistributore;
    }

    public String getDistRagSociale() {
        return distRagSociale;
    }

    public void setDistRagSociale(String distRagSociale) {
        this.distRagSociale = distRagSociale;
    }

    public String getDistDescrizione() {
        return distDescrizione;
    }

    public void setDistDescrizione(String distDescrizione) {
        this.distDescrizione = distDescrizione;
    }

    public String getDistIndirizzo() {
        return distIndirizzo;
    }

    public void setDistIndirizzo(String distIndirizzo) {
        this.distIndirizzo = distIndirizzo;
    }

    public String getDistCitta() {
        return distCitta;
    }

    public void setDistCitta(String distCitta) {
        this.distCitta = distCitta;
    }

    public String getDistCap() {
        return distCap;
    }

    public void setDistCap(String distCap) {
        this.distCap = distCap;
    }

    public String getDistTel1() {
        return distTel1;
    }

    public void setDistTel1(String distTel1) {
        this.distTel1 = distTel1;
    }

    public String getDistTel2() {
        return distTel2;
    }

    public void setDistTel2(String distTel2) {
        this.distTel2 = distTel2;
    }

    public String getDistFax() {
        return distFax;
    }

    public void setDistFax(String distFax) {
        this.distFax = distFax;
    }

    public String getDistEmail() {
        return distEmail;
    }

    public void setDistEmail(String distEmail) {
        this.distEmail = distEmail;
    }

    public Date getDistDataCreazione() {
        return distDataCreazione;
    }

    public void setDistDataCreazione(Date distDataCreazione) {
        this.distDataCreazione = distDataCreazione;
    }

    public Date getDistDataCessazione() {
        return distDataCessazione;
    }

    public void setDistDataCessazione(Date distDataCessazione) {
        this.distDataCessazione = distDataCessazione;
    }

    public String getDistCF() {
        return distCF;
    }

    public void setDistCF(String distCF) {
        this.distCF = distCF;
    }

    public String getDistEmail2() {
        return distEmail2;
    }

    public void setDistEmail2(String distEmail2) {
        this.distEmail2 = distEmail2;
    }

    public String getDistPIVA() {
        return distPIVA;
    }

    public void setDistPIVA(String distPIVA) {
        this.distPIVA = distPIVA;
    }
    
    @JsonBackReference
    public List<Utente> getUtenteList() {
        return utenteList;
    }

    public void setUtenteList(List<Utente> utenteList) {
        this.utenteList = utenteList;
    }
    
    @JsonManagedReference
    public Credenzialiutente getCredenzialiutente() {
        return credenzialiutente;
    }
    
    public void setCredenzialiutente(Credenzialiutente credenzialiutente) {
        this.credenzialiutente = credenzialiutente;
    }
    
    @JsonManagedReference
    public Gestore getGestore() {
        return gestore;
    }
    
    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistributore != null ? idDistributore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributore)) {
            return false;
        }
        Distributore other = (Distributore) object;
        if ((this.idDistributore == null && other.idDistributore != null) || (this.idDistributore != null && !this.idDistributore.equals(other.idDistributore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Distributore[idDistributore=" + idDistributore + "]";
    }

}