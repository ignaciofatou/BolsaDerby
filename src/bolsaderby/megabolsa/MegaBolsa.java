/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby.megabolsa;

import bolsaderby.librerias.Fecha;
import bolsaderby.data.DatoValor;
import bolsaderby.data.DatoValorPK;
import bolsaderby.data.FicheroTratado;
import bolsaderby.data.FicherosTratados;
import bolsaderby.data.PatronDato;
import bolsaderby.data.PatronesDatos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
    
    //Constantes para los Datos Extraidos
    private final String COD_VALOR = "COD_VALOR";
    private final String FECHA     = "FECHA";
    private final String APERTURA  = "APERTURA";
    private final String MAXIMO    = "MAXIMO";
    private final String MINIMO    = "MINIMO";
    private final String CIERRE    = "CIERRE";
    private final String VOLUMEN   = "VOLUMEN";
    
    //Constructor
    public MegaBolsa(EntityManager entityManager, String direccionWEB, String comodinWEB, String extensionWEB, String categoria){
        //Asignamos la Conexion
        this.entityManager = entityManager;

        //Obtenemos los Patrones para Recuperar Ordenadamente los Campos de la Web
        PatronesDatos auxPatronesCampos = new PatronesDatos();
        patronesCampos = auxPatronesCampos.findAll(entityManager);
        
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
                    datoValor = getDatoValor(patronesCampos, lineaLeida);

                    //Añadimos el Nuevo Dato del Valor a la Base de Datos
                    if (!insertaDatoValorBBDD(datoValor))
                        System.out.println("No se pudo Insertar el Dato: " + datoValor.getDatoValorPK().getCodValor() + " - Fecha: " + datoValor.getDatoValorPK().getFecha().toString());
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
    
	//Construye a Partir de una Linea y del Patron
    public DatoValor getDatoValor(List <PatronDato> patronesCampos, String linea){
        //Para guardar los Datos del Valor
        DatoValorPK datoValorPK = new DatoValorPK();
        DatoValor   datoValor   = new DatoValor();
        
        //A partir de la Linea guardamos los Datos en un List de Campos
        CamposLinea camposLinea = new CamposLinea(linea, ",");

        //Recorremos el List de Patrones
        for(PatronDato patronDato:patronesCampos){
            
            //Recuperamos el Campo especificado en la Posicion del Patron
            int posicion = patronDato.getOrden();
            CampoLinea campoLinea = camposLinea.getCamposLinea().get(posicion - 1);
            String contenido = campoLinea.getContenido();
            
            switch(patronDato.getCodCampo()){
                case COD_VALOR:
                    datoValorPK.setCodValor(contenido);
                    break;
                case FECHA:
                    Date fecha = Fecha.getFechaDate(contenido, Fecha.YYYYMMDD);
                    datoValorPK.setFecha(fecha);
                    break;
                case APERTURA:
                    datoValor.setApertura(BigDecimal.valueOf(Double.valueOf(contenido)));
                    break;
                case MAXIMO:
                    datoValor.setMaximo(BigDecimal.valueOf(Double.valueOf(contenido)));
                    break;
                case MINIMO:
                    datoValor.setMinimo(BigDecimal.valueOf(Double.valueOf(contenido)));
                    break;
                case CIERRE:
                    datoValor.setCierre(BigDecimal.valueOf(Double.valueOf(contenido)));
                    break;
                case VOLUMEN:
                    datoValor.setVolumen(Long.valueOf(contenido));
                    break;
            }
        }
        //Guardamos la Primary Key
        datoValor.setDatoValorPK(datoValorPK);
        
        //Retornamos el DatoValor
        return datoValor;
    }
    
    
    private boolean insertaDatoValorBBDD(DatoValor datoValor){
        try{
            entityManager.getTransaction().begin(); 
            entityManager.persist(datoValor); 
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println("Error al Insertar en la Tabla DATOS_VALORES");
            System.out.println(ex.toString());
            return false;
        }
    }
    
    private boolean insertaFicheroTratadoBBDD(FicheroTratado ficheroTratado){
        try{
            entityManager.getTransaction().begin(); 
            entityManager.persist(ficheroTratado); 
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception ex){
            System.out.println("Error al Insertar en la Tabla FICHERO_TRATADO");
            System.out.println(ex.toString());
            return false;
        }
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
