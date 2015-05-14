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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ignacio
 */
@Embeddable
public class DatoValorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "COD_VALOR")
    private String codValor;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public DatoValorPK() {
    }

    public DatoValorPK(String codValor, Date fecha) {
        this.codValor = codValor;
        this.fecha = fecha;
    }

    public String getCodValor() {
        return codValor;
    }

    public void setCodValor(String codValor) {
        this.codValor = codValor;
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
        hash += (codValor != null ? codValor.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoValorPK)) {
            return false;
        }
        DatoValorPK other = (DatoValorPK) object;
        if ((this.codValor == null && other.codValor != null) || (this.codValor != null && !this.codValor.equals(other.codValor))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.resources.DatoValorPK[ codValor=" + codValor + ", fecha=" + fecha + " ]";
    }
    
}
