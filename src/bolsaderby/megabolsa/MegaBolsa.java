/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.megabolsa;

import bolsaderby.librerias.Fecha;
import bolsaderby.data.DatoValor;
import bolsaderby.data.FicheroTratado;
import bolsaderby.data.FicherosTratados;
import bolsaderby.data.PatronDato;
import bolsaderby.data.PatronesDatos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ignacio
  * http://www.megabolsa.com/cierres/150410.txt
  * http://www.megabolsa.com/cierres/150402I.txt
 */
public class MegaBolsa extends Thread{
    
    //Atributos de Conexion a Base de Datos
    EntityManager entityManager;
    
    //Atributos
    private List <PatronDato> patronesCampos;
    private String direccionWEB;
    private String comodinWEB;
    private String extensionWEB;
    private String categoria;
    private int    diaEnProceso = 0;
    
    //Constantes
    public final int NUM_DIAS = 300;
    
    //Constructor
    public MegaBolsa(EntityManager entityManager, String direccionWEB, String comodinWEB, String extensionWEB, String categoria){
        //Asignamos la Conexion
        this.entityManager = entityManager;

        //Obtenemos los Patrones para Recuperar Ordenadamente los Campos de la Web
        PatronesDatos auxPatronesCampos = new PatronesDatos();
        patronesCampos = auxPatronesCampos.findAll(null);
        
        //Obtenemos el Resto de Datos para Generar la URL
        this.direccionWEB = direccionWEB;
        this.comodinWEB   = comodinWEB;
        this.extensionWEB = extensionWEB;
        this.categoria    = categoria;
    }
    
    //Recupera los Datos de la Web megabolsa.com y los Inserta en la Base de Datos
    private void setDatosToBBDD(String fecha){
        //Retorna los Datos de los Valores Para un Dia
        DatoDiaValores datoDiaValores = new DatoDiaValores();
        DatoValor      datoValor;
        
        //URL desde la que vamos a Obtener la Informacion
        String direccionInformacion = this.direccionWEB + fecha + this.comodinWEB + this.extensionWEB;
        
        //Comprobamos que el Fichero no haya sido Ya tratado
        if (!FicherosTratados.estaYaTratado(entityManager, direccionInformacion)){
            System.out.println("Tratamos Fichero: "+direccionInformacion);
            //Variables Para leer la URL
            URL accesoURL;
            String lineaLeida;

            try
            {
                //Accedemos a la URL
                accesoURL = new URL(direccionInformacion);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(accesoURL.openStream())); 

                //Recorremos el Fichero de la URL hasta el Final
                while ((lineaLeida = buffer.readLine()) != null){
                    //Por Cada Linea Creamos un Nuevo Dato Valor
                    datoValor = new DatoValor(patronesCampos, lineaLeida);                

                    //Añadimos el Nuevo Dato del Valor a la Base de Datos
                    insertaDatoValorBBDD(datoValor);
                }
                //Cerramos el Buffer
                buffer.close();

            }catch (Exception e){
                //Suele dar este error con los Fines de Semana que no hay Datos para la Bolsa
                System.out.println("Error al Acceder al Fichero: " + e.getMessage());
            }
            //Insertamos el Fichero Tratado (Para no volver a tratarlo)
            FicheroTratado fichTratado = new FicheroTratado(direccionInformacion, datoDiaValores.getDatoDiaValores().size());
            insertaFicheroTratadoBBDD(fichTratado);
            System.out.println("El Fichero Ha Sido Tratado Con Exito: "+direccionInformacion);
        }
        //else
        //    System.out.println("El Fichero Ya Fue Tratado: "+direccionInformacion);
    }
    
    private void insertaDatoValorBBDD(DatoValor datoValor){
        entityManager.getTransaction().begin(); 
        entityManager.persist(datoValor); 
        entityManager.getTransaction().commit();
    }
    
    private void insertaFicheroTratadoBBDD(FicheroTratado ficheroTratado){
        entityManager.getTransaction().begin(); 
        entityManager.persist(ficheroTratado); 
        entityManager.getTransaction().commit();
    }
    
    //Actualiza los Datos de los Valores el Numero de dias Indicado en NUM_DIAS
    private void updateDatosBBDD(){

        //Obtenemos la Fecha Actual
        Calendar cal  = Calendar.getInstance(); 
        Date datFecha = cal.getTime();
        String strFecha;
        
        for (diaEnProceso = 0; diaEnProceso < NUM_DIAS; diaEnProceso++){
            //Restamos un dia a la Fecha
            datFecha = Fecha.sumarRestarDiasFecha(datFecha, -1);
            
            //Sacamos la Fecha formateada
            strFecha = Fecha.getStrFecha(datFecha, "YYMMdd");
            setDatosToBBDD(strFecha);
        }
    }
    
    @Override
    public void run(){
        updateDatosBBDD();
    }

    /**
     * @return the diaEnProceso
     */
    public int getDiaEnProceso() {
        return diaEnProceso;
    }
}
