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
public class Categorias {
    private List<Categoria> categoriaList;

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }            

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }
    
    public List<Categoria> findAll(EntityManager entityManager) {
        // Load data from database
        Query query = entityManager.createNamedQuery("Categorias.findAll");
        categoriaList = query.getResultList();
        return categoriaList;
    } 
}
