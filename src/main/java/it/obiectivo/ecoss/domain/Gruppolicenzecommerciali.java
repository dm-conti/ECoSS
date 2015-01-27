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
@Table(name = "gruppolicenzecommerciali", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Gruppolicenzecommerciali.findAll", query = "SELECT g FROM Gruppolicenzecommerciali g"),
    @NamedQuery(name = "Gruppolicenzecommerciali.findByIdGruppiLicenzeCommerciali", query = "SELECT g FROM Gruppolicenzecommerciali g WHERE g.idGruppiLicenzeCommerciali = :idGruppiLicenzeCommerciali")})
public class Gruppolicenzecommerciali implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGruppiLicenzeCommerciali")
    private Integer idGruppiLicenzeCommerciali;
    
    @JoinColumn(name = "voceLicenza_idVoceLicenza", referencedColumnName = "idVoceLicenza")
    @ManyToOne(optional = false)
    private Vocelicenza vocelicenza;
    
    @JoinColumn(name = "licenzeCommerciali_idLicenzeCommerciali", referencedColumnName = "idLicenzeCommerciali")
    @ManyToOne(optional = false)
    private Licenzecommerciali licenzecommerciali;

    public Gruppolicenzecommerciali() {
    }

    public Gruppolicenzecommerciali(Integer idGruppiLicenzeCommerciali) {
        this.idGruppiLicenzeCommerciali = idGruppiLicenzeCommerciali;
    }

    public Integer getIdGruppiLicenzeCommerciali() {
        return idGruppiLicenzeCommerciali;
    }

    public void setIdGruppiLicenzeCommerciali(Integer idGruppiLicenzeCommerciali) {
        this.idGruppiLicenzeCommerciali = idGruppiLicenzeCommerciali;
    }

    @JsonManagedReference
    public Vocelicenza getVocelicenza() {
        return vocelicenza;
    }

    
    public void setVocelicenza(Vocelicenza vocelicenza) {
        this.vocelicenza = vocelicenza;
    }

    @JsonManagedReference
    public Licenzecommerciali getLicenzecommerciali() {
        return licenzecommerciali;
    }

    public void setLicenzecommerciali(Licenzecommerciali licenzecommerciali) {
        this.licenzecommerciali = licenzecommerciali;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGruppiLicenzeCommerciali != null ? idGruppiLicenzeCommerciali.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruppolicenzecommerciali)) {
            return false;
        }
        Gruppolicenzecommerciali other = (Gruppolicenzecommerciali) object;
        if ((this.idGruppiLicenzeCommerciali == null && other.idGruppiLicenzeCommerciali != null) || (this.idGruppiLicenzeCommerciali != null && !this.idGruppiLicenzeCommerciali.equals(other.idGruppiLicenzeCommerciali))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Gruppolicenzecommerciali[idGruppiLicenzeCommerciali=" + idGruppiLicenzeCommerciali + "]";
    }

}