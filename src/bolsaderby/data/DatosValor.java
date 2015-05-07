/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ignacio
 */
public class DatosValor {
    private List<DatoValor> datoValorList;

    public List<DatoValor> getDatoValorList() {
        return datoValorList;
    }            

    public void setDatoValorList(List<DatoValor> categoriaList) {
        this.datoValorList = categoriaList;
    }
    
    public List<DatoValor> findAllDatosValor(EntityManager entityManager, String codValor) {
        // Load data from database
        Query query = entityManager.createNamedQuery("DatoValor.findByCodValor");
        query.setParameter("codValor", codValor);
        datoValorList = query.getResultList();
        return datoValorList;
    } 
}
