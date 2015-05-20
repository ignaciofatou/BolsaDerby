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
    private List<PatronDato> patronDatosList;

    public List<PatronDato> getPatronDatosList() {
        return patronDatosList;
    }            

    public void setPatronDatosList(List<PatronDato> patronDatosList) {
        this.patronDatosList = patronDatosList;
    }
    
    public List<PatronDato> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("PatronDato.findAll");
        patronDatosList = query.getResultList();
        return patronDatosList;
    } 
}
