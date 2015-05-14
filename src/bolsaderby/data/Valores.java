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
public class Valores {
    private List<Valor> valorList;

    public List<Valor> getValorList() {
        return valorList;
    }            

    public void setValorList(List<Valor> valorList) {
        this.valorList = valorList;
    }
    
    public List<Valor> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Valor.findAll");
        valorList = query.getResultList();
        return valorList;
    } 
    public List<Valor> findByCodCat(EntityManager entityManager, ) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Valor.findAll");
        valorList = query.getResultList();
        return valorList;
    } 
}
