/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "profilo", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Profilo.findAll", query = "SELECT p FROM Profilo p"),
    @NamedQuery(name = "Profilo.findByIdProfilo", query = "SELECT p FROM Profilo p WHERE p.idProfilo = :idProfilo"),
    @NamedQuery(name = "Profilo.findByProfDescrizione", query = "SELECT p FROM Profilo p WHERE p.profDescrizione = :profDescrizione")})
public class Profilo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProfilo")
    private Integer idProfilo;
    
    @Basic(optional = false)
    @Column(name = "profDescrizione")
    private String profDescrizione;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilo")
    private List<Credenzialiutente> credenzialiutenteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilo")
    private List<Funzraggruppate> funzraggruppateList;

    public Profilo() {
    }

    public Profilo(Integer idProfilo) {
        this.idProfilo = idProfilo;
    }

    public Profilo(Integer idProfilo, String profDescrizione) {
        this.idProfilo = idProfilo;
        this.profDescrizione = profDescrizione;
    }

    public Integer getIdProfilo() {
        return idProfilo;
    }

    public void setIdProfilo(Integer idProfilo) {
        this.idProfilo = idProfilo;
    }

    public String getProfDescrizione() {
        return profDescrizione;
    }

    public void setProfDescrizione(String profDescrizione) {
        this.profDescrizione = profDescrizione;
    }
    
    @JsonBackReference
    public List<Credenzialiutente> getCredenzialiutenteList() {
        return credenzialiutenteList;
    }
    
    public void setCredenzialiutenteList(List<Credenzialiutente> credenzialiutenteList) {
        this.credenzialiutenteList = credenzialiutenteList;
    }
    
    @JsonBackReference
    public List<Funzraggruppate> getFunzraggruppateList() {
        return funzraggruppateList;
    }

    public void setFunzraggruppateList(List<Funzraggruppate> funzraggruppateList) {
        this.funzraggruppateList = funzraggruppateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfilo != null ? idProfilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profilo)) {
            return false;
        }
        Profilo other = (Profilo) object;
        if ((this.idProfilo == null && other.idProfilo != null) || (this.idProfilo != null && !this.idProfilo.equals(other.idProfilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Profilo[idProfilo=" + idProfilo + "]";
    }

}