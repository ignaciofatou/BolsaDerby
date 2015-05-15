/*
 * Lo vamos a Usar para Descargar los Datos de un Solo dia de varios Valores
  */
package bolsaderby.megabolsa;

import bolsaderby.data.DatoValor;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class DatoDiaValores {
    //Atributos
    private List <DatoValor> datoDiaValores;
    private String fecha;
    
    //Constructor
    public DatoDiaValores(){
        datoDiaValores = new ArrayList();
    }

    //Incluimos un Nuevo Valor
    public void setNuevoValor(DatoValor datoValor){
        datoDiaValores.add(datoValor);
    }
    /**
     * @return the datoDiaValores
     */
    public List <DatoValor> getDatoDiaValores() {
        return datoDiaValores;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }    
    
    //Recorremos todos los Datos de los Valores y los Insertamos en la BBDD
    public void insertaDatoValoresBBDD(){
        for (DatoValor datoValor:datoDiaValores){
            //datoValor.insertaDatoValorBBDD();
        }
    }
}
