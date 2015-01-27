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
import javax.persistence.Lob;
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
@Table(name = "cpe", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cpe.findAll", query = "SELECT c FROM Cpe c"),
    @NamedQuery(name = "Cpe.findByIdCPE", query = "SELECT c FROM Cpe c WHERE c.idCPE = :idCPE"),
    @NamedQuery(name = "Cpe.findByCpeNome", query = "SELECT c FROM Cpe c WHERE c.cpeNome = :cpeNome"),
    @NamedQuery(name = "Cpe.findByCpeModello", query = "SELECT c FROM Cpe c WHERE c.cpeModello = :cpeModello"),
    @NamedQuery(name = "Cpe.findByCpeVersCore", query = "SELECT c FROM Cpe c WHERE c.cpeVersCore = :cpeVersCore"),
    @NamedQuery(name = "Cpe.findByCpeVersCoreProg", query = "SELECT c FROM Cpe c WHERE c.cpeVersCoreProg = :cpeVersCoreProg"),
    @NamedQuery(name = "Cpe.findByCpeIpAddress", query = "SELECT c FROM Cpe c WHERE c.cpeIpAddress = :cpeIpAddress"),
    @NamedQuery(name = "Cpe.findByCpeMacAddress", query = "SELECT c FROM Cpe c WHERE c.cpeMacAddress = :cpeMacAddress"),
    @NamedQuery(name = "Cpe.findByCpeDataAttivazione", query = "SELECT c FROM Cpe c WHERE c.cpeDataAttivazione = :cpeDataAttivazione"),
    @NamedQuery(name = "Cpe.findByCpeCompanyKey", query = "SELECT c FROM Cpe c WHERE c.cpeCompanyKey = :cpeCompanyKey"),
    @NamedQuery(name = "Cpe.findByCpeRegistrationPort", query = "SELECT c FROM Cpe c WHERE c.cpeRegistrationPort = :cpeRegistrationPort")})
public class Cpe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCPE")
    private Integer idCPE;
    
    @Basic(optional = false)
    @Column(name = "cpeNome")
    private String cpeNome;
    
    @Basic(optional = false)
    @Column(name = "cpeModello")
    private String cpeModello;
    
    @Basic(optional = false)
    @Column(name = "cpeVersCore")
    private String cpeVersCore;
    
    @Basic(optional = false)
    @Column(name = "cpeVersCoreProg")
    private String cpeVersCoreProg;
    
    @Basic(optional = false)
    @Column(name = "cpeIpAddress")
    private String cpeIpAddress;
    
    @Basic(optional = false)
    @Column(name = "cpeMacAddress")
    private String cpeMacAddress;
    
    @Basic(optional = false)
    @Column(name = "cpeDataAttivazione")
    @Temporal(TemporalType.DATE)
    private Date cpeDataAttivazione;
    
    @Lob
    @Column(name = "cpeManagementKey")
    private String cpeManagementKey;
    
    @Column(name = "cpeCompanyKey")
    private String cpeCompanyKey;
    
    @Column(name = "cpeRegistrationPort")
    private Integer cpeRegistrationPort;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpe")
    private List<Licenzacpe> licenzacpeList;
    
    @JoinColumn(name = "fk_Gestore_ID", referencedColumnName = "idGestore")
    @ManyToOne(optional = false)
    private Gestore gestore;
    
    @JoinColumn(name = "cpeStato", referencedColumnName = "idstato")
    @ManyToOne(optional = false)
    private Tabellastati tabellastati;

    public Cpe() {
    }

    public Cpe(Integer idCPE) {
        this.idCPE = idCPE;
    }

    public Cpe(Integer idCPE, String cpeNome, String cpeModello, String cpeVersCore, String cpeVersCoreProg, String cpeIpAddress, String cpeMacAddress, Date cpeDataAttivazione) {
        this.idCPE = idCPE;
        this.cpeNome = cpeNome;
        this.cpeModello = cpeModello;
        this.cpeVersCore = cpeVersCore;
        this.cpeVersCoreProg = cpeVersCoreProg;
        this.cpeIpAddress = cpeIpAddress;
        this.cpeMacAddress = cpeMacAddress;
        this.cpeDataAttivazione = cpeDataAttivazione;
    }

    public Integer getIdCPE() {
        return idCPE;
    }

    public void setIdCPE(Integer idCPE) {
        this.idCPE = idCPE;
    }

    public String getCpeNome() {
        return cpeNome;
    }

    public void setCpeNome(String cpeNome) {
        this.cpeNome = cpeNome;
    }

    public String getCpeModello() {
        return cpeModello;
    }

    public void setCpeModello(String cpeModello) {
        this.cpeModello = cpeModello;
    }

    public String getCpeVersCore() {
        return cpeVersCore;
    }

    public void setCpeVersCore(String cpeVersCore) {
        this.cpeVersCore = cpeVersCore;
    }

    public String getCpeVersCoreProg() {
        return cpeVersCoreProg;
    }

    public void setCpeVersCoreProg(String cpeVersCoreProg) {
        this.cpeVersCoreProg = cpeVersCoreProg;
    }

    public String getCpeIpAddress() {
        return cpeIpAddress;
    }

    public void setCpeIpAddress(String cpeIpAddress) {
        this.cpeIpAddress = cpeIpAddress;
    }

    public String getCpeMacAddress() {
        return cpeMacAddress;
    }

    public void setCpeMacAddress(String cpeMacAddress) {
        this.cpeMacAddress = cpeMacAddress;
    }

    public Date getCpeDataAttivazione() {
        return cpeDataAttivazione;
    }

    public void setCpeDataAttivazione(Date cpeDataAttivazione) {
        this.cpeDataAttivazione = cpeDataAttivazione;
    }

    public String getCpeManagementKey() {
        return cpeManagementKey;
    }

    public void setCpeManagementKey(String cpeManagementKey) {
        this.cpeManagementKey = cpeManagementKey;
    }

    public String getCpeCompanyKey() {
        return cpeCompanyKey;
    }

    public void setCpeCompanyKey(String cpeCompanyKey) {
        this.cpeCompanyKey = cpeCompanyKey;
    }

    public Integer getCpeRegistrationPort() {
        return cpeRegistrationPort;
    }

    public void setCpeRegistrationPort(Integer cpeRegistrationPort) {
        this.cpeRegistrationPort = cpeRegistrationPort;
    }
    
    @JsonBackReference
    public List<Licenzacpe> getLicenzacpeList() {
        return licenzacpeList;
    }
    
    public void setLicenzacpeList(List<Licenzacpe> licenzacpeList) {
        this.licenzacpeList = licenzacpeList;
    }
    
    @JsonManagedReference
    public Gestore getGestore() {
        return gestore;
    }
    
    public void setGestore(Gestore gestore) {
        this.gestore = gestore;
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
        hash += (idCPE != null ? idCPE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpe)) {
            return false;
        }
        Cpe other = (Cpe) object;
        if ((this.idCPE == null && other.idCPE != null) || (this.idCPE != null && !this.idCPE.equals(other.idCPE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Cpe[idCPE=" + idCPE + "]";
    }

}