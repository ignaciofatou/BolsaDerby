/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "VALORES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valor.findAll", query = "SELECT v FROM Valor v"),
    //@NamedQuery(name = "Valor.findByCodCat", query = "SELECT v FROM Valor v WHERE v.codCat = :codCat"),
    @NamedQuery(name = "Valor.findByCodValor", query = "SELECT v FROM Valor v WHERE v.codValor = :codValor"),
    @NamedQuery(name = "Valor.findByDecimales", query = "SELECT v FROM Valor v WHERE v.decimales = :decimales"),
    @NamedQuery(name = "Valor.findByDescripcion", query = "SELECT v FROM Valor v WHERE v.descripcion = :descripcion")})
public class Valor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_VALOR")
    private String codValor;
    @Basic(optional = false)
    @Column(name = "DECIMALES")
    private int decimales;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "COD_CAT", referencedColumnName = "COD_CAT")
    @ManyToOne(optional = false)
    private Categoria codCat;
    //private String codCat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "valor")
    private Collection<DatoValor> datoValorCollection;

    public Valor() {
    }

    public Valor(String codValor) {
        this.codValor = codValor;
    }

    public Valor(String codValor, int decimales, String descripcion) {
        this.codValor = codValor;
        this.decimales = decimales;
        this.descripcion = descripcion;
    }

    public String getCodValor() {
        return codValor;
    }

    public void setCodValor(String codValor) {
        this.codValor = codValor;
    }

    public int getDecimales() {
        return decimales;
    }

    public void setDecimales(int decimales) {
        this.decimales = decimales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCodCat() {
        return codCat;
    }

    public void setCodCat(Categoria codCat) {
        this.codCat = codCat;
    }

    @XmlTransient
    public Collection<DatoValor> getDatoValorCollection() {
        return datoValorCollection;
    }

    public void setDatoValorCollection(Collection<DatoValor> datoValorCollection) {
        this.datoValorCollection = datoValorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codValor != null ? codValor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valor)) {
            return false;
        }
        Valor other = (Valor) object;
        if ((this.codValor == null && other.codValor != null) || (this.codValor != null && !this.codValor.equals(other.codValor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.resources.Valor[ codValor=" + codValor + " ]";
    }
    
}
