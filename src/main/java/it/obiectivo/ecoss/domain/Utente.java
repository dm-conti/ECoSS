/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "utente", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u"),
    @NamedQuery(name = "Utente.findByIdUtente", query = "SELECT u FROM Utente u WHERE u.idUtente = :idUtente"),
    @NamedQuery(name = "Utente.findByUteNome", query = "SELECT u FROM Utente u WHERE u.uteNome = :uteNome"),
    @NamedQuery(name = "Utente.findByUteCognome", query = "SELECT u FROM Utente u WHERE u.uteCognome = :uteCognome"),
    @NamedQuery(name = "Utente.findByUteRagSociale", query = "SELECT u FROM Utente u WHERE u.uteRagSociale = :uteRagSociale"),
    @NamedQuery(name = "Utente.findByUteDescrizione", query = "SELECT u FROM Utente u WHERE u.uteDescrizione = :uteDescrizione"),
    @NamedQuery(name = "Utente.findByUteIndirizzo", query = "SELECT u FROM Utente u WHERE u.uteIndirizzo = :uteIndirizzo"),
    @NamedQuery(name = "Utente.findByUteCitta", query = "SELECT u FROM Utente u WHERE u.uteCitta = :uteCitta"),
    @NamedQuery(name = "Utente.findByUteCap", query = "SELECT u FROM Utente u WHERE u.uteCap = :uteCap"),
    @NamedQuery(name = "Utente.findByUteTel1", query = "SELECT u FROM Utente u WHERE u.uteTel1 = :uteTel1"),
    @NamedQuery(name = "Utente.findByUteTel2", query = "SELECT u FROM Utente u WHERE u.uteTel2 = :uteTel2"),
    @NamedQuery(name = "Utente.findByUteFax", query = "SELECT u FROM Utente u WHERE u.uteFax = :uteFax"),
    @NamedQuery(name = "Utente.findByUteEmail", query = "SELECT u FROM Utente u WHERE u.uteEmail = :uteEmail"),
    @NamedQuery(name = "Utente.findByUteDataCreazione", query = "SELECT u FROM Utente u WHERE u.uteDataCreazione = :uteDataCreazione"),
    @NamedQuery(name = "Utente.findByUteDataCessazione", query = "SELECT u FROM Utente u WHERE u.uteDataCessazione = :uteDataCessazione"),
    @NamedQuery(name = "Utente.findByUteCF", query = "SELECT u FROM Utente u WHERE u.uteCF = :uteCF"),
    @NamedQuery(name = "Utente.findByUteEmail2", query = "SELECT u FROM Utente u WHERE u.uteEmail2 = :uteEmail2"),
    @NamedQuery(name = "Utente.findByUtePIVA", query = "SELECT u FROM Utente u WHERE u.utePIVA = :utePIVA")})
public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtente")
    private Integer idUtente;
    
    @NotNull(message = "Il nome utente non puo' essere NotNull.")
    @NotEmpty(message = "Il nome utente deve essere specificato.")
    @Column(name = "uteNome")
    private String uteNome;
    
    @NotNull
    @Column(name = "uteCognome")
    private String uteCognome;
    
    @Column(name = "uteRagSociale")
    private String uteRagSociale;
    
    @Column(name = "uteDescrizione")
    private String uteDescrizione;
    
    @Column(name = "uteIndirizzo")
    private String uteIndirizzo;
    
    @Column(name = "uteCitta")
    private String uteCitta;
    
    @Column(name = "uteCap")
    private String uteCap;
    
    @Column(name = "uteTel1")
    private String uteTel1;
    
    @Column(name = "uteTel2")
    private String uteTel2;
    
    @Column(name = "uteFax")
    private String uteFax;
    
    @Column(name = "uteEmail")
    private String uteEmail;
    
    @Basic(optional = false)
    @Column(name = "uteDataCreazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uteDataCreazione;
    
    @Column(name = "uteDataCessazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uteDataCessazione;
    
    @Column(name = "uteCF")
    private String uteCF;
    
    @Column(name = "uteEmail2")
    private String uteEmail2;
    
    @Column(name = "utePIVA")
    private String utePIVA;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Istanzautentecpe> istanzautentecpeList;
    
    @JoinColumn(name = "gestore_idGestore", referencedColumnName = "idGestore")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Gestore gestore;
    
    @JoinColumn(name = "distributore_idDistributore", referencedColumnName = "idDistributore")
    @ManyToOne(fetch = FetchType.EAGER)
    private Distributore distributore;
    
    @JoinColumn(name = "credenziali_idCredenziali", referencedColumnName = "IdCredenzialiUtente")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Credenzialiutente credenzialiutente;

    public Utente() {
    }

    public Utente(Integer idUtente) {
        this.idUtente = idUtente;
    }
    
    /* QUESTO COSTRUTTORE E' MAPPATO DIRETTAMENTE SULLA MASCHERA DI
     * INPUT: utente/New.jsp - TALI INFORMAZIONI DA SOLE NON BASTANO
     * PER MEMORIZZARE CORRETTAMENTE L'Utente ALL'INTERNO DEL DB. 
     * PER QUESTO MOTIVO BISOGNA SETTARE ESTERNAMENTE ALTRI PARAMETRI
     * COME AD ESEMPIO: gestore */
    public Utente(Map<String, String> map){
    	this.uteNome = (String) map.get("uteNome");
    	this.uteCognome = (String) map.get(uteCognome);
    	this.uteRagSociale = (String) map.get("uteRagSociale");
    	this.uteDescrizione = (String) map.get("uteDescrizione");
    	this.uteIndirizzo = (String) map.get("uteIndirizzo");
    	this.uteCitta = (String) map.get("uteCitta");
    	this.uteCap = (String) map.get("uteCap");
    	this.uteTel1 = (String) map.get("uteTel1");
    	this.uteTel2 = (String) map.get("uteTel2");
    	this.uteFax = (String) map.get("uteFax");
    	this.uteEmail = (String) map.get("uteEmail");
    	this.uteEmail2 = (String) map.get("uteEmail2");
    }

    public Utente(Integer idUtente, Date uteDataCreazione) {
        this.idUtente = idUtente;
        this.uteDataCreazione = uteDataCreazione;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getUteNome() {
        return uteNome;
    }

    public void setUteNome(String uteNome) {
        this.uteNome = uteNome;
    }

    public String getUteCognome() {
        return uteCognome;
    }

    public void setUteCognome(String uteCognome) {
        this.uteCognome = uteCognome;
    }

    public String getUteRagSociale() {
        return uteRagSociale;
    }

    public void setUteRagSociale(String uteRagSociale) {
        this.uteRagSociale = uteRagSociale;
    }

    public String getUteDescrizione() {
        return uteDescrizione;
    }

    public void setUteDescrizione(String uteDescrizione) {
        this.uteDescrizione = uteDescrizione;
    }

    public String getUteIndirizzo() {
        return uteIndirizzo;
    }

    public void setUteIndirizzo(String uteIndirizzo) {
        this.uteIndirizzo = uteIndirizzo;
    }

    public String getUteCitta() {
        return uteCitta;
    }

    public void setUteCitta(String uteCitta) {
        this.uteCitta = uteCitta;
    }

    public String getUteCap() {
        return uteCap;
    }

    public void setUteCap(String uteCap) {
        this.uteCap = uteCap;
    }

    public String getUteTel1() {
        return uteTel1;
    }

    public void setUteTel1(String uteTel1) {
        this.uteTel1 = uteTel1;
    }

    public String getUteTel2() {
        return uteTel2;
    }

    public void setUteTel2(String uteTel2) {
        this.uteTel2 = uteTel2;
    }

    public String getUteFax() {
        return uteFax;
    }

    public void setUteFax(String uteFax) {
        this.uteFax = uteFax;
    }

    public String getUteEmail() {
        return uteEmail;
    }

    public void setUteEmail(String uteEmail) {
        this.uteEmail = uteEmail;
    }

    public Date getUteDataCreazione() {
        return uteDataCreazione;
    }

    public void setUteDataCreazione(Date uteDataCreazione) {
        this.uteDataCreazione = uteDataCreazione;
    }

    public Date getUteDataCessazione() {
        return uteDataCessazione;
    }

    public void setUteDataCessazione(Date uteDataCessazione) {
        this.uteDataCessazione = uteDataCessazione;
    }

    public String getUteCF() {
        return uteCF;
    }

    public void setUteCF(String uteCF) {
        this.uteCF = uteCF;
    }

    public String getUteEmail2() {
        return uteEmail2;
    }

    public void setUteEmail2(String uteEmail2) {
        this.uteEmail2 = uteEmail2;
    }

    public String getUtePIVA() {
        return utePIVA;
    }

    public void setUtePIVA(String utePIVA) {
        this.utePIVA = utePIVA;
    }

    @JsonBackReference
    public List<Istanzautentecpe> getIstanzautentecpeList() {
        return istanzautentecpeList;
    }
    
    public void setIstanzautentecpeList(List<Istanzautentecpe> istanzautentecpeList) {
        this.istanzautentecpeList = istanzautentecpeList;
    }
    
    @JsonManagedReference
    public Gestore getGestore() {
        return gestore;
    }
    
    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
    }
    
    @JsonManagedReference
    public Distributore getDistributore() {
        return distributore;
    }
    
    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }
    
    @JsonManagedReference
    public Credenzialiutente getCredenzialiutente() {
        return credenzialiutente;
    }

    public void setCredenzialiutente(Credenzialiutente credenzialiutente) {
        this.credenzialiutente = credenzialiutente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtente != null ? idUtente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utente)) {
            return false;
        }
        Utente other = (Utente) object;
        if ((this.idUtente == null && other.idUtente != null) || (this.idUtente != null && !this.idUtente.equals(other.idUtente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Utente[idUtente=" + idUtente + "]";
    }
}