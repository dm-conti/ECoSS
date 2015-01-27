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
@Table(name = "funzione", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Funzione.findAll", query = "SELECT f FROM Funzione f"),
    @NamedQuery(name = "Funzione.findByIdFunzione", query = "SELECT f FROM Funzione f WHERE f.idFunzione = :idFunzione"),
    @NamedQuery(name = "Funzione.findByNomeFunzione", query = "SELECT f FROM Funzione f WHERE f.nomeFunzione = :nomeFunzione")})
public class Funzione implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFunzione")
    private Integer idFunzione;
    
    @Column(name = "nomeFunzione")
    private String nomeFunzione;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funzione")
    private List<Funzraggruppate> funzraggruppateList;

    public Funzione() {
    }

    public Funzione(Integer idFunzione) {
        this.idFunzione = idFunzione;
    }

    public Integer getIdFunzione() {
        return idFunzione;
    }

    public void setIdFunzione(Integer idFunzione) {
        this.idFunzione = idFunzione;
    }

    public String getNomeFunzione() {
        return nomeFunzione;
    }

    public void setNomeFunzione(String nomeFunzione) {
        this.nomeFunzione = nomeFunzione;
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
        hash += (idFunzione != null ? idFunzione.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funzione)) {
            return false;
        }
        Funzione other = (Funzione) object;
        if ((this.idFunzione == null && other.idFunzione != null) || (this.idFunzione != null && !this.idFunzione.equals(other.idFunzione))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Funzione[idFunzione=" + idFunzione + "]";
    }

}