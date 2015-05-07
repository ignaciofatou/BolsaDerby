/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ignacio
 */
@Entity
@Table(name = "PATRON_DATOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatronDatos.findAll", query = "SELECT p FROM PatronDatos p"),
    @NamedQuery(name = "PatronDatos.findByCodCampo", query = "SELECT p FROM PatronDatos p WHERE p.codCampo = :codCampo"),
    @NamedQuery(name = "PatronDatos.findByOrden", query = "SELECT p FROM PatronDatos p WHERE p.orden = :orden"),
    @NamedQuery(name = "PatronDatos.findByTipoDato", query = "SELECT p FROM PatronDatos p WHERE p.tipoDato = :tipoDato")})
public class PatronDatos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_CAMPO")
    private String codCampo;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private int orden;
    @Basic(optional = false)
    @Column(name = "TIPO_DATO")
    private String tipoDato;

    public PatronDatos() {
    }

    public PatronDatos(String codCampo) {
        this.codCampo = codCampo;
    }

    public PatronDatos(String codCampo, int orden, String tipoDato) {
        this.codCampo = codCampo;
        this.orden = orden;
        this.tipoDato = tipoDato;
    }

    public String getCodCampo() {
        return codCampo;
    }

    public void setCodCampo(String codCampo) {
        this.codCampo = codCampo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCampo != null ? codCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatronDatos)) {
            return false;
        }
        PatronDatos other = (PatronDatos) object;
        if ((this.codCampo == null && other.codCampo != null) || (this.codCampo != null && !this.codCampo.equals(other.codCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bolsaderby.data.PatronDatos[ codCampo=" + codCampo + " ]";
    }
    
}
