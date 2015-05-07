/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "FICHEROS_TRATADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FicheroTratado.findAll", query = "SELECT f FROM FICHEROS_TRATADOS f"),
    @NamedQuery(name = "FicheroTratado.findByFichero", query = "SELECT f FROM FICHEROS_TRATADOS f WHERE f.fichero = :fichero"),
    @NamedQuery(name = "FicheroTratado.findByNumReg", query = "SELECT f FROM FICHEROS_TRATADOS f WHERE f.numReg = :numReg"),
    @NamedQuery(name = "FicheroTratado.findByFecha", query = "SELECT f FROM FICHEROS_TRATADOS f WHERE f.fecha = :fecha")})
public class FicheroTratado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FICHERO")
    private String fichero;
    @Basic(optional = false)
    @Column(name = "NUM_REG")
    private int numReg;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public FicheroTratado() {
    }

    public FicheroTratado(String fichero) {
        this.fichero = fichero;
    }

    public FicheroTratado(String fichero, int numReg) {
        this.fichero = fichero;
        this.numReg = numReg;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }

    public int getNumReg() {
        return numReg;
    }

    public void setNumReg(int numReg) {
        this.numReg = numReg;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fichero != null ? fichero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FicheroTratado)) {
            return false;
        }
        FicheroTratado other = (FicheroTratado) object;
        if ((this.fichero == null && other.fichero != null) || (this.fichero != null && !this.fichero.equals(other.fichero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.data.FicherosTratados[ fichero=" + fichero + " ]";
    }
    
}
