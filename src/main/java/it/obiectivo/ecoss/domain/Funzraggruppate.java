/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.obiectivo.ecoss.domain;

import java.io.Serializable;
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
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "funzraggruppate", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Funzraggruppate.findAll", query = "SELECT f FROM Funzraggruppate f"),
    @NamedQuery(name = "Funzraggruppate.findByIdFunzRaggruppate", query = "SELECT f FROM Funzraggruppate f WHERE f.idFunzRaggruppate = :idFunzRaggruppate")})
public class Funzraggruppate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFunzRaggruppate")
    private Integer idFunzRaggruppate;
    
    @JoinColumn(name = "funzione_idFunzione", referencedColumnName = "idFunzione")
    @ManyToOne(optional = false)
    private Funzione funzione;
    
    @JoinColumn(name = "profilo_idProfilo", referencedColumnName = "idProfilo")
    @ManyToOne(optional = false)
    private Profilo profilo;

    public Funzraggruppate() {
    }

    public Funzraggruppate(Integer idFunzRaggruppate) {
        this.idFunzRaggruppate = idFunzRaggruppate;
    }

    public Integer getIdFunzRaggruppate() {
        return idFunzRaggruppate;
    }

    public void setIdFunzRaggruppate(Integer idFunzRaggruppate) {
        this.idFunzRaggruppate = idFunzRaggruppate;
    }
    
    @JsonManagedReference
    public Funzione getFunzione() {
        return funzione;
    }

    public void setFunzione(Funzione funzione) {
        this.funzione = funzione;
    }

    @JsonManagedReference
    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFunzRaggruppate != null ? idFunzRaggruppate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funzraggruppate)) {
            return false;
        }
        Funzraggruppate other = (Funzraggruppate) object;
        if ((this.idFunzRaggruppate == null && other.idFunzRaggruppate != null) || (this.idFunzRaggruppate != null && !this.idFunzRaggruppate.equals(other.idFunzRaggruppate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Funzraggruppate[idFunzRaggruppate=" + idFunzRaggruppate + "]";
    }

}