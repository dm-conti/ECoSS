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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "gestore", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Gestore.findAll", query = "SELECT g FROM Gestore g"),
    @NamedQuery(name = "Gestore.findByIdGestore", query = "SELECT g FROM Gestore g WHERE g.idGestore = :idGestore"),
    @NamedQuery(name = "Gestore.findByGestRagSociale", query = "SELECT g FROM Gestore g WHERE g.gestRagSociale = :gestRagSociale"),
    @NamedQuery(name = "Gestore.findByGestDescrizione", query = "SELECT g FROM Gestore g WHERE g.gestDescrizione = :gestDescrizione"),
    @NamedQuery(name = "Gestore.findByGestIndirizzo", query = "SELECT g FROM Gestore g WHERE g.gestIndirizzo = :gestIndirizzo"),
    @NamedQuery(name = "Gestore.findByGestCitta", query = "SELECT g FROM Gestore g WHERE g.gestCitta = :gestCitta"),
    @NamedQuery(name = "Gestore.findByGestCap", query = "SELECT g FROM Gestore g WHERE g.gestCap = :gestCap"),
    @NamedQuery(name = "Gestore.findByGestTel1", query = "SELECT g FROM Gestore g WHERE g.gestTel1 = :gestTel1"),
    @NamedQuery(name = "Gestore.findByGestTel2", query = "SELECT g FROM Gestore g WHERE g.gestTel2 = :gestTel2"),
    @NamedQuery(name = "Gestore.findByGestFax", query = "SELECT g FROM Gestore g WHERE g.gestFax = :gestFax"),
    @NamedQuery(name = "Gestore.findByGestEmail", query = "SELECT g FROM Gestore g WHERE g.gestEmail = :gestEmail"),
    @NamedQuery(name = "Gestore.findByGestLivello", query = "SELECT g FROM Gestore g WHERE g.gestLivello = :gestLivello"),
    @NamedQuery(name = "Gestore.findByGestDataCreazione", query = "SELECT g FROM Gestore g WHERE g.gestDataCreazione = :gestDataCreazione"),
    @NamedQuery(name = "Gestore.findByGestDataCessazione", query = "SELECT g FROM Gestore g WHERE g.gestDataCessazione = :gestDataCessazione"),
    @NamedQuery(name = "Gestore.findByGestEmail2", query = "SELECT g FROM Gestore g WHERE g.gestEmail2 = :gestEmail2"),
    @NamedQuery(name = "Gestore.findByGestCF", query = "SELECT g FROM Gestore g WHERE g.gestCF = :gestCF"),
    @NamedQuery(name = "Gestore.findByGestPIVA", query = "SELECT g FROM Gestore g WHERE g.gestPIVA = :gestPIVA")})
public class Gestore implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGestore")
    private Integer idGestore;
    
    @Column(name = "gestRagSociale")
    private String gestRagSociale;
    
    @Column(name = "gestDescrizione")
    private String gestDescrizione;
    
    @Column(name = "gestIndirizzo")
    private String gestIndirizzo;
    
    @Column(name = "gestCitta")
    private String gestCitta;
    
    @Column(name = "gestCap")
    private String gestCap;
    
    @Column(name = "gestTel1")
    private String gestTel1;
    
    @Column(name = "gestTel2")
    private String gestTel2;
    
    @Column(name = "gestFax")
    private String gestFax;
    
    @Column(name = "gestEmail")
    private String gestEmail;
    
    @Basic(optional = false)
    @Column(name = "gestLivello")
    private int gestLivello;
    
    @Basic(optional = false)
    @Column(name = "gestDataCreazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gestDataCreazione;
    
    @Column(name = "gestDataCessazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gestDataCessazione;
    
    @Column(name = "gestEmail2")
    private String gestEmail2;
    
    @Column(name = "gestCF")
    private String gestCF;
    
    @Column(name = "gestPIVA")
    private String gestPIVA;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gestore")
    private List<Utente> utenteList;
    
    @JoinColumn(name = "credenziali_idCredenziali", referencedColumnName = "IdCredenzialiUtente")
    @OneToOne
    private Credenzialiutente credenzialiutente;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gestore")
    private List<Distributore> distributoreList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gestore", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Cpe> cpeList;

    public Gestore() {
    }

    public Gestore(Integer idGestore) {
        this.idGestore = idGestore;
    }

    public Gestore(Integer idGestore, int gestLivello, Date gestDataCreazione) {
        this.idGestore = idGestore;
        this.gestLivello = gestLivello;
        this.gestDataCreazione = gestDataCreazione;
    }

    public Integer getIdGestore() {
        return idGestore;
    }

    public void setIdGestore(Integer idGestore) {
        this.idGestore = idGestore;
    }

    public String getGestRagSociale() {
        return gestRagSociale;
    }

    public void setGestRagSociale(String gestRagSociale) {
        this.gestRagSociale = gestRagSociale;
    }

    public String getGestDescrizione() {
        return gestDescrizione;
    }

    public void setGestDescrizione(String gestDescrizione) {
        this.gestDescrizione = gestDescrizione;
    }

    public String getGestIndirizzo() {
        return gestIndirizzo;
    }

    public void setGestIndirizzo(String gestIndirizzo) {
        this.gestIndirizzo = gestIndirizzo;
    }

    public String getGestCitta() {
        return gestCitta;
    }

    public void setGestCitta(String gestCitta) {
        this.gestCitta = gestCitta;
    }

    public String getGestCap() {
        return gestCap;
    }

    public void setGestCap(String gestCap) {
        this.gestCap = gestCap;
    }

    public String getGestTel1() {
        return gestTel1;
    }

    public void setGestTel1(String gestTel1) {
        this.gestTel1 = gestTel1;
    }

    public String getGestTel2() {
        return gestTel2;
    }

    public void setGestTel2(String gestTel2) {
        this.gestTel2 = gestTel2;
    }

    public String getGestFax() {
        return gestFax;
    }

    public void setGestFax(String gestFax) {
        this.gestFax = gestFax;
    }

    public String getGestEmail() {
        return gestEmail;
    }

    public void setGestEmail(String gestEmail) {
        this.gestEmail = gestEmail;
    }

    public int getGestLivello() {
        return gestLivello;
    }

    public void setGestLivello(int gestLivello) {
        this.gestLivello = gestLivello;
    }

    public Date getGestDataCreazione() {
        return gestDataCreazione;
    }

    public void setGestDataCreazione(Date gestDataCreazione) {
        this.gestDataCreazione = gestDataCreazione;
    }

    public Date getGestDataCessazione() {
        return gestDataCessazione;
    }

    public void setGestDataCessazione(Date gestDataCessazione) {
        this.gestDataCessazione = gestDataCessazione;
    }

    public String getGestEmail2() {
        return gestEmail2;
    }

    public void setGestEmail2(String gestEmail2) {
        this.gestEmail2 = gestEmail2;
    }

    public String getGestCF() {
        return gestCF;
    }

    public void setGestCF(String gestCF) {
        this.gestCF = gestCF;
    }

    public String getGestPIVA() {
        return gestPIVA;
    }

    public void setGestPIVA(String gestPIVA) {
        this.gestPIVA = gestPIVA;
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

    @JsonBackReference
    public List<Distributore> getDistributoreList() {
        return distributoreList;
    }

    public void setDistributoreList(List<Distributore> distributoreList) {
        this.distributoreList = distributoreList;
    }
    
    @JsonBackReference
    public List<Cpe> getCpeList() {
        return cpeList;
    }
    
    public void setCpeList(List<Cpe> cpeList) {
        this.cpeList = cpeList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGestore != null ? idGestore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gestore)) {
            return false;
        }
        Gestore other = (Gestore) object;
        if ((this.idGestore == null && other.idGestore != null) || (this.idGestore != null && !this.idGestore.equals(other.idGestore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Gestore[idGestore=" + idGestore + "]";
    }

}
