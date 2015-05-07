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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "OPERACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operaciones.findAll", query = "SELECT o FROM Operaciones o"),
    @NamedQuery(name = "Operaciones.findById", query = "SELECT o FROM Operaciones o WHERE o.id = :id"),
    @NamedQuery(name = "Operaciones.findByCodValor", query = "SELECT o FROM Operaciones o WHERE o.codValor = :codValor"),
    @NamedQuery(name = "Operaciones.findByVolumen", query = "SELECT o FROM Operaciones o WHERE o.volumen = :volumen"),
    @NamedQuery(name = "Operaciones.findByDesOperacion", query = "SELECT o FROM Operaciones o WHERE o.desOperacion = :desOperacion"),
    @NamedQuery(name = "Operaciones.findByFechApertura", query = "SELECT o FROM Operaciones o WHERE o.fechApertura = :fechApertura"),
    @NamedQuery(name = "Operaciones.findByPrecApertura", query = "SELECT o FROM Operaciones o WHERE o.precApertura = :precApertura"),
    @NamedQuery(name = "Operaciones.findByFechCierre", query = "SELECT o FROM Operaciones o WHERE o.fechCierre = :fechCierre"),
    @NamedQuery(name = "Operaciones.findByPrecCierre", query = "SELECT o FROM Operaciones o WHERE o.precCierre = :precCierre"),
    @NamedQuery(name = "Operaciones.findByTasas", query = "SELECT o FROM Operaciones o WHERE o.tasas = :tasas"),
    @NamedQuery(name = "Operaciones.findByBeneficio", query = "SELECT o FROM Operaciones o WHERE o.beneficio = :beneficio")})
public class Operacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "COD_VALOR")
    private String codValor;
    @Basic(optional = false)
    @Column(name = "VOLUMEN")
    private double volumen;
    @Basic(optional = false)
    @Column(name = "DES_OPERACION")
    private String desOperacion;
    @Basic(optional = false)
    @Column(name = "FECH_APERTURA")
    @Temporal(TemporalType.DATE)
    private Date fechApertura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PREC_APERTURA")
    private BigDecimal precApertura;
    @Column(name = "FECH_CIERRE")
    @Temporal(TemporalType.DATE)
    private Date fechCierre;
    @Column(name = "PREC_CIERRE")
    private BigDecimal precCierre;
    @Column(name = "TASAS")
    private BigDecimal tasas;
    @Column(name = "BENEFICIO")
    private BigDecimal beneficio;

    public Operacion() {
    }

    public Operacion(Integer id) {
        this.id = id;
    }

    public Operacion(Integer id, String codValor, double volumen, String desOperacion, Date fechApertura, BigDecimal precApertura) {
        this.id = id;
        this.codValor = codValor;
        this.volumen = volumen;
        this.desOperacion = desOperacion;
        this.fechApertura = fechApertura;
        this.precApertura = precApertura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodValor() {
        return codValor;
    }

    public void setCodValor(String codValor) {
        this.codValor = codValor;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public String getDesOperacion() {
        return desOperacion;
    }

    public void setDesOperacion(String desOperacion) {
        this.desOperacion = desOperacion;
    }

    public Date getFechApertura() {
        return fechApertura;
    }

    public void setFechApertura(Date fechApertura) {
        this.fechApertura = fechApertura;
    }

    public BigDecimal getPrecApertura() {
        return precApertura;
    }

    public void setPrecApertura(BigDecimal precApertura) {
        this.precApertura = precApertura;
    }

    public Date getFechCierre() {
        return fechCierre;
    }

    public void setFechCierre(Date fechCierre) {
        this.fechCierre = fechCierre;
    }

    public BigDecimal getPrecCierre() {
        return precCierre;
    }

    public void setPrecCierre(BigDecimal precCierre) {
        this.precCierre = precCierre;
    }

    public BigDecimal getTasas() {
        return tasas;
    }

    public void setTasas(BigDecimal tasas) {
        this.tasas = tasas;
    }

    public BigDecimal getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(BigDecimal beneficio) {
        this.beneficio = beneficio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operacion)) {
            return false;
        }
        Operacion other = (Operacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.data.Operaciones[ id=" + id + " ]";
    }
    
}
