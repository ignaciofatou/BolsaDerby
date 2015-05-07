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
public class PatronesDatos {
    private List<PatronDatos> patronDatosList;

    public List<PatronDatos> getPatronDatosList() {
        return patronDatosList;
    }            

    public void setPatronDatosList(List<PatronDatos> patronDatosList) {
        this.patronDatosList = patronDatosList;
    }
    
    public List<PatronDatos> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("PatronDatos.findAll");
        patronDatosList = query.getResultList();
        return patronDatosList;
    } 
}