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

/**
 *
 * @author dconti
 */
@Entity
@Table(name = "licenzecommerciali", catalog = "ecoss", schema = "")
@NamedQueries({
    @NamedQuery(name = "Licenzecommerciali.findAll", query = "SELECT l FROM Licenzecommerciali l"),
    @NamedQuery(name = "Licenzecommerciali.findByIdLicenzeCommerciali", query = "SELECT l FROM Licenzecommerciali l WHERE l.idLicenzeCommerciali = :idLicenzeCommerciali"),
    @NamedQuery(name = "Licenzecommerciali.findByLicomNomeLicenza", query = "SELECT l FROM Licenzecommerciali l WHERE l.licomNomeLicenza = :licomNomeLicenza")})
public class Licenzecommerciali implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLicenzeCommerciali")
    private Integer idLicenzeCommerciali;
    
    @Basic(optional = false)
    @Column(name = "licomNomeLicenza")
    private String licomNomeLicenza;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenzecommerciali")
    private List<Gruppolicenzecommerciali> gruppolicenzecommercialiList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licenzecommerciali")
    private List<Licenzautente> licenzautenteList;

    public Licenzecommerciali() {
    }

    public Licenzecommerciali(Integer idLicenzeCommerciali) {
        this.idLicenzeCommerciali = idLicenzeCommerciali;
    }

    public Licenzecommerciali(Integer idLicenzeCommerciali, String licomNomeLicenza) {
        this.idLicenzeCommerciali = idLicenzeCommerciali;
        this.licomNomeLicenza = licomNomeLicenza;
    }

    public Integer getIdLicenzeCommerciali() {
        return idLicenzeCommerciali;
    }

    public void setIdLicenzeCommerciali(Integer idLicenzeCommerciali) {
        this.idLicenzeCommerciali = idLicenzeCommerciali;
    }

    public String getLicomNomeLicenza() {
        return licomNomeLicenza;
    }

    public void setLicomNomeLicenza(String licomNomeLicenza) {
        this.licomNomeLicenza = licomNomeLicenza;
    }
    
    @JsonBackReference
    public List<Gruppolicenzecommerciali> getGruppolicenzecommercialiList() {
        return gruppolicenzecommercialiList;
    }
    
    public void setGruppolicenzecommercialiList(List<Gruppolicenzecommerciali> gruppolicenzecommercialiList) {
        this.gruppolicenzecommercialiList = gruppolicenzecommercialiList;
    }
    
    @JsonBackReference
    public List<Licenzautente> getLicenzautenteList() {
        return licenzautenteList;
    }
    
    public void setLicenzautenteList(List<Licenzautente> licenzautenteList) {
        this.licenzautenteList = licenzautenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLicenzeCommerciali != null ? idLicenzeCommerciali.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licenzecommerciali)) {
            return false;
        }
        Licenzecommerciali other = (Licenzecommerciali) object;
        if ((this.idLicenzeCommerciali == null && other.idLicenzeCommerciali != null) || (this.idLicenzeCommerciali != null && !this.idLicenzeCommerciali.equals(other.idLicenzeCommerciali))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.obiectivo.ecoss.domain.Licenzecommerciali[idLicenzeCommerciali=" + idLicenzeCommerciali + "]";
    }
}