/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import bolsaderby.librerias.Fecha;
import bolsaderby.megabolsa.CampoLinea;
import bolsaderby.megabolsa.CamposLinea;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
    @NamedQuery(name = "DatoValor.findAll", query = "SELECT d FROM DatoValor d"),
    @NamedQuery(name = "DatoValor.findByCodValor", query = "SELECT d FROM DatoValor d WHERE d.datoValorPK.codValor = :codValor"),
    @NamedQuery(name = "DatoValor.findByFecha", query = "SELECT d FROM DatoValor d WHERE d.datoValorPK.fecha = :fecha"),
    @NamedQuery(name = "DatoValor.findByApertura", query = "SELECT d FROM DatoValor d WHERE d.apertura = :apertura"),
    @NamedQuery(name = "DatoValor.findByMaximo", query = "SELECT d FROM DatoValor d WHERE d.maximo = :maximo"),
    @NamedQuery(name = "DatoValor.findByMinimo", query = "SELECT d FROM DatoValor d WHERE d.minimo = :minimo"),
    @NamedQuery(name = "DatoValor.findByCierre", query = "SELECT d FROM DatoValor d WHERE d.cierre = :cierre"),
    @NamedQuery(name = "DatoValor.findByVolumen", query = "SELECT d FROM DatoValor d WHERE d.volumen = :volumen")})
public class DatoValor implements Serializable {
    
    //Constantes
    private final String COD_VALOR = "COD_VALOR";
    private final String FECHA     = "FECHA";
    private final String APERTURA  = "APERTURA";
    private final String MAXIMO    = "MAXIMO";
    private final String MINIMO    = "MINIMO";
    private final String CIERRE    = "CIERRE";
    private final String VOLUMEN   = "VOLUMEN";
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DatoValorPK datoValorPK;
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
    private Valor valor;

    public DatoValor() {
    }

    public DatoValor(DatoValorPK datoValorPK) {
        this.datoValorPK = datoValorPK;
    }

    public DatoValor(DatoValorPK datoValorPK, BigDecimal apertura, BigDecimal maximo, BigDecimal minimo, long volumen) {
        this.datoValorPK = datoValorPK;
        this.apertura = apertura;
        this.maximo = maximo;
        this.minimo = minimo;
        this.volumen = volumen;
    }

    //Construye a Partir de una Linea y del Patron
    public DatoValor(List <PatronDato> patronesCampos, String linea){
        //Para la primary key
        DatoValorPK datoValorPK = new DatoValorPK();
        
        //A partir de la Linea guardamos los Datos en un List de Campos
        CamposLinea camposLinea = new CamposLinea(linea, ",");

        //Recorremos el List de Patrones
        for(PatronDato patronDato:patronesCampos){
            
            //Recuperamos el Campo especificado en la Posicion del Patron
            int posicion = patronDato.getOrden();
            CampoLinea campoLinea = camposLinea.getCamposLinea().get(posicion - 1);
            String contenido = campoLinea.getContenido();
            
            switch(patronDato.getCodCampo()){
                case COD_VALOR:
                    datoValorPK.setCodValor(contenido);
                    break;
                case FECHA:
                    Date fecha = Fecha.getFechaDate(contenido, Fecha.YYYYMMDD);
                    datoValorPK.setFecha(fecha);
                    break;
                case APERTURA:
                    this.apertura = BigDecimal.valueOf(Double.valueOf(contenido));
                    break;
                case MAXIMO:
                    this.maximo = BigDecimal.valueOf(Double.valueOf(contenido));
                    break;
                case MINIMO:
                    this.minimo = BigDecimal.valueOf(Double.valueOf(contenido));
                    break;
                case CIERRE:
                    this.cierre = BigDecimal.valueOf(Double.valueOf(contenido));
                    break;
                case VOLUMEN:
                    this.volumen = Long.valueOf(contenido);
                    break;
            }
        }
        //Guardamos la Primary Key
        this.datoValorPK = datoValorPK;
    }
    
    public DatoValor(String codValor, Date fecha) {
        this.datoValorPK = new DatoValorPK(codValor, fecha);
    }

    public DatoValorPK getDatoValorPK() {
        return datoValorPK;
    }

    public void setDatoValorPK(DatoValorPK datoValorPK) {
        this.datoValorPK = datoValorPK;
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

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datoValorPK != null ? datoValorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoValor)) {
            return false;
        }
        DatoValor other = (DatoValor) object;
        if ((this.datoValorPK == null && other.datoValorPK != null) || (this.datoValorPK != null && !this.datoValorPK.equals(other.datoValorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.resources.DatoValor[ datoValorPK=" + datoValorPK + " ]";
    }
    
}
