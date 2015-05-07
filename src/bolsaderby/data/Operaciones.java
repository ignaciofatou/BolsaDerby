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
public class Operaciones {
    private List<Operacion> operacionList;

    public List<Operacion> getOperacionList() {
        return operacionList;
    }            

    public void setOperacionList(List<Operacion> operacionList) {
        this.operacionList = operacionList;
    }
    
    public List<Operacion> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Operaciones.findAll");
        operacionList = query.getResultList();
        return operacionList;
    }
    
    public List<Operacion> findAllValor(EntityManager entityManager, String codValor) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Operaciones.findByCodValor");
        query.setParameter("codValor", codValor);
        operacionList = query.getResultList();
        return operacionList;
    } 
}
