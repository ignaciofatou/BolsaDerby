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
public class FicherosTratados {
    private List<FicheroTratado> ficheroTratadoList;

    public List<FicheroTratado> getFicheroTratadoList() {
        return ficheroTratadoList;
    }            

    public void setFicheroTratadoList(List<FicheroTratado> ficheroTratadoList) {
        this.ficheroTratadoList = ficheroTratadoList;
    }
    
    public List<FicheroTratado> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("FicherosTratados.findAll");
        ficheroTratadoList = query.getResultList();
        return ficheroTratadoList;
    }
}