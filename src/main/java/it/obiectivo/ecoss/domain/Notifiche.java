package it.obiectivo.ecoss.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "notifiche", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Notifiche.findAll", query = "SELECT n FROM Notifiche n"),
    @NamedQuery(name = "Notifiche.findByIdNotifiche", query = "SELECT n FROM Notifiche n WHERE n.idNotifiche = :idNotifiche"),
    @NamedQuery(name = "Notifiche.findByIdTabella", query = "SELECT n FROM Notifiche n WHERE n.idTabella = :idTabella"),
    @NamedQuery(name = "Notifiche.findByNomeTabella", query = "SELECT n FROM Notifiche n WHERE n.nomeTabella = :nomeTabella"),
    @NamedQuery(name = "Notifiche.findByCodNotifica", query = "SELECT n FROM Notifiche n WHERE n.codNotifica = :codNotifica"),
    @NamedQuery(name = "Notifiche.findRow", query = "SELECT n FROM Notifiche n WHERE n.idTabella = :idTabella AND n.nomeTabella = :nomeTabella AND n.codNotifica = :codNotifica")})
public class Notifiche implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotifiche")
    private Integer idNotifiche;
    
    @Basic(optional = false)
    @Column(name = "idTabella")
    private int idTabella;
    
    @Basic(optional = false)
    @Column(name = "nomeTabella")
    private String nomeTabella;
    
    @Basic(optional = false)
    @Column(name = "codNotifica")
    private String codNotifica;

    public Notifiche() {
    }

    public Notifiche(Integer idNotifiche) {
        this.idNotifiche = idNotifiche;
    }

    public Notifiche(int idTabella, String nomeTabella, String codNotifica) {
        this.idTabella = idTabella;
        this.nomeTabella = nomeTabella;
        this.codNotifica = codNotifica;
    }

    public Integer getIdNotifiche() {
        return idNotifiche;
    }

    public void setIdNotifiche(Integer idNotifiche) {
        this.idNotifiche = idNotifiche;
    }

    public int getIdTabella() {
        return idTabella;
    }

    public void setIdTabella(int idTabella) {
        this.idTabella = idTabella;
    }

    public String getNomeTabella() {
        return nomeTabella;
    }

    public void setNomeTabella(String nomeTabella) {
        this.nomeTabella = nomeTabella;
    }

    public String getCodNotifica() {
        return codNotifica;
    }

    public void setCodNotifica(String codNotifica) {
        this.codNotifica = codNotifica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotifiche != null ? idNotifiche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifiche)) {
            return false;
        }
        Notifiche other = (Notifiche) object;
        if ((this.idNotifiche == null && other.idNotifiche != null) || (this.idNotifiche != null && !this.idNotifiche.equals(other.idNotifiche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Notifiche[idNotifiche=" + idNotifiche + "]";
    }

}