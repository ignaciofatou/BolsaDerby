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
public class Indicadores {
    private List<Indicador> indicadorList;

    public List<Indicador> getIndicadorList() {
        return indicadorList;
    }            

    public void setIndicadorList(List<Indicador> indicadorList) {
        this.indicadorList = indicadorList;
    }
    
    public List<Indicador> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Indicadores.findAll");
        indicadorList = query.getResultList();
        return indicadorList;
    }
}
