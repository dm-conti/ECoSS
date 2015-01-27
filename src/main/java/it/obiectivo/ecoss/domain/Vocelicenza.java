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
@Table(name = "vocelicenza", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Vocelicenza.findAll", query = "SELECT v FROM Vocelicenza v"),
    @NamedQuery(name = "Vocelicenza.findByIdVoceLicenza", query = "SELECT v FROM Vocelicenza v WHERE v.idVoceLicenza = :idVoceLicenza"),
    @NamedQuery(name = "Vocelicenza.findByVoceLicenza", query = "SELECT v FROM Vocelicenza v WHERE v.voceLicenza = :voceLicenza")})
public class Vocelicenza implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVoceLicenza")
    private Integer idVoceLicenza;
    
    @Basic(optional = false)
    @Column(name = "voceLicenza")
    private String voceLicenza;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vocelicenza")
    private List<Gruppolicenzecommerciali> gruppolicenzecommercialiList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vocelicenza")
    private List<Servizilicenzacpe> servizilicenzacpeList;

    public Vocelicenza() {
    }

    public Vocelicenza(Integer idVoceLicenza) {
        this.idVoceLicenza = idVoceLicenza;
    }

    public Vocelicenza(Integer idVoceLicenza, String voceLicenza) {
        this.idVoceLicenza = idVoceLicenza;
        this.voceLicenza = voceLicenza;
    }

    public Integer getIdVoceLicenza() {
        return idVoceLicenza;
    }

    public void setIdVoceLicenza(Integer idVoceLicenza) {
        this.idVoceLicenza = idVoceLicenza;
    }

    public String getVoceLicenza() {
        return voceLicenza;
    }

    public void setVoceLicenza(String voceLicenza) {
        this.voceLicenza = voceLicenza;
    }
    
    @JsonBackReference
    public List<Gruppolicenzecommerciali> getGruppolicenzecommercialiList() {
        return gruppolicenzecommercialiList;
    }
    
    
    public void setGruppolicenzecommercialiList(List<Gruppolicenzecommerciali> gruppolicenzecommercialiList) {
        this.gruppolicenzecommercialiList = gruppolicenzecommercialiList;
    }

    @JsonBackReference
    public List<Servizilicenzacpe> getServizilicenzacpeList() {
        return servizilicenzacpeList;
    }

    public void setServizilicenzacpeList(List<Servizilicenzacpe> servizilicenzacpeList) {
        this.servizilicenzacpeList = servizilicenzacpeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoceLicenza != null ? idVoceLicenza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vocelicenza)) {
            return false;
        }
        Vocelicenza other = (Vocelicenza) object;
        if ((this.idVoceLicenza == null && other.idVoceLicenza != null) || (this.idVoceLicenza != null && !this.idVoceLicenza.equals(other.idVoceLicenza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Vocelicenza[idVoceLicenza=" + idVoceLicenza + "]";
    }

}