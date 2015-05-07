/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "DATOS_VALORES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoValor.findAll", query = "SELECT d FROM DatosValores d"),
    @NamedQuery(name = "DatoValor.findByCodValor", query = "SELECT d FROM DatosValores d WHERE d.datosValoresPK.codValor = :codValor"),
    @NamedQuery(name = "DatoValor.findByCodValorFecha", query = "SELECT d FROM DatosValores d WHERE d.datosValoresPK.codValor = :codValor AND d.datosValoresPK.fecha = :fecha")})
public class DatoValor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatoValorPK datosValoresPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "APERTURA")
    private BigDecimal apertura;
    @Basic(optional = false)
    @Column(name = "MAXIMO")
    private BigDecimal maximo;
    @Basic(optional = false)
    @Column(name = "MINIMO")
    private BigDecimal minimo;
    @Column(name = "CIERRE")
    private BigDecimal cierre;
    @Basic(optional = false)
    @Column(name = "VOLUMEN")
    private long volumen;
    @JoinColumn(name = "COD_VALOR", referencedColumnName = "COD_VALOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Valor valores;

    public DatoValor() {
    }

    public DatoValor(DatoValorPK datosValoresPK) {
        this.datosValoresPK = datosValoresPK;
    }

    public DatoValor(DatoValorPK datosValoresPK, BigDecimal apertura, BigDecimal maximo, BigDecimal minimo, long volumen) {
        this.datosValoresPK = datosValoresPK;
        this.apertura = apertura;
        this.maximo = maximo;
        this.minimo = minimo;
        this.volumen = volumen;
    }

    public DatoValor(String codValor, Date fecha) {
        this.datosValoresPK = new DatoValorPK(codValor, fecha);
    }

    public DatoValorPK getDatosValoresPK() {
        return datosValoresPK;
    }

    public void setDatosValoresPK(DatoValorPK datosValoresPK) {
        this.datosValoresPK = datosValoresPK;
    }

    public BigDecimal getApertura() {
        return apertura;
    }

    public void setApertura(BigDecimal apertura) {
        this.apertura = apertura;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

    public BigDecimal getMinimo() {
        return minimo;
    }

    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    public BigDecimal getCierre() {
        return cierre;
    }

    public void setCierre(BigDecimal cierre) {
        this.cierre = cierre;
    }

    public long getVolumen() {
        return volumen;
    }

    public void setVolumen(long volumen) {
        this.volumen = volumen;
    }

    public Valor getValores() {
        return valores;
    }

    public void setValores(Valor valores) {
        this.valores = valores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datosValoresPK != null ? datosValoresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoValor)) {
            return false;
        }
        DatoValor other = (DatoValor) object;
        if ((this.datosValoresPK == null && other.datosValoresPK != null) || (this.datosValoresPK != null && !this.datosValoresPK.equals(other.datosValoresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.data.DatosValores[ datosValoresPK=" + datosValoresPK + " ]";
    }
    
}
