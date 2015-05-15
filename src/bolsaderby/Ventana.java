/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolsaderby;

import bolsaderby.data.Categoria;
import bolsaderby.data.Categorias;
import bolsaderby.data.DatoValor;
import bolsaderby.data.DatosValor;
import bolsaderby.data.Valor;
import bolsaderby.model.DatosValorTableModel;
import bolsaderby.resources.BolsaValues;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ignacio
 */
public class Ventana extends javax.swing.JFrame {

    // Connection with database using an entity manager
    EntityManager   entityManager = Persistence.createEntityManagerFactory("BolsaDerbyPU").createEntityManager();
    Categorias      categorias;
    List<Valor>     valores;
    Valor           valor;
    DatosValor      datosValor;          
    
    private DatosValorTableModel datosValorTableModel;
    private DatoValor            datoValorSelected;
    
    /**
     * Creates new form Ventana
     */
    public Ventana() {
        //Inicializa los Componentes de la Ventana
        initComponents();
        
        //Ponemos el Layout (Diseño) a Nulo
        this.setLayout(null);
        
        // Save default background color to be used en jTableDatosValor
        BolsaValues.defaultBackgroundColor = jTableDatosValor.getSelectionBackground();
        
        //Centramos la ventana
        setLocationRelativeTo(null);
        
        //Cargamos las Categorias en el ComboBox
        cargaCategorias();
        
        //Inicializamos el JTable de Datos del Valor
        cargaJTableDatosValor();
    }
    
    // Set configuration and data content for students jTable
    private void cargaJTableDatosValor() {
        // Model for JTable, assigning classgroups content
        datosValorTableModel = new DatosValorTableModel(datosValor);
        jTableDatosValor.setModel(datosValorTableModel);  

        // Allow only one row selected
        jTableDatosValor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Listen for student selection in jtable
        jTableDatosValor.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    int indexSelectedRow = jTableDatosValor.getSelectedRow();
                    if(indexSelectedRow >= 0) {
                        datoValorSelected = datosValor.getDatoValorList().get(indexSelectedRow);
                        //showDetailStudentSelected();
                    } else {
                        //jTextFieldName.setText("");
                        //jTextFieldSurnames.setText("");
                        //jTextFieldGrade.setText("");
                    }
                }
            }
        );
        
        //enableEditingStudentDetail(false);
    }

    //Solo se deben Cargar las Categorias al Iniciar el Programa
    private void cargaCategorias(){
        //Reiniciamos el Combo si ya tiene algun contenido
        if (jCBCategorias.getItemCount() != 0)
            reiniciaCombo(jCBCategorias);
        
        //Recuperamos todas las Categorias
        categorias = new Categorias();
        categorias.findAll(entityManager);
        
        //Cargamos el ComboBox a Partir de las Descripciones de las Categorias
        for (Categoria categoria:categorias.getCategoriaList()){
            jCBCategorias.addItem(categoria.getDescripcion());
        }
        //Marcamos por defecto el Primero Item como Seleccionado
        jCBCategorias.setSelectedIndex(0);
        
        //Carga los Valores de la Categoria Seleccionada por defecto
        cargaValoresCategoria();
    }
    
    //Carga todos los Valores de la Categoria Seleccionada
    private void cargaValoresCategoria(){
        //Recuperamos los Valores de la Categoria Seleccionada
        valores = getValoresCategoria();
        
        //Reiniciamos el Combo si ya tiene algun contenido
        if (jCBValores.getItemCount() != 0)
            reiniciaCombo(jCBValores);
        
        //Cargamos el ComboBox a Partir de las Descripciones de las Categorias
        for (Valor valor:valores){
            jCBValores.addItem(valor.getDescripcion());
        }
        //Marcamos por defecto el Primero Item como Seleccionado
        jCBValores.setSelectedIndex(0);
        
        //Carga los Datos del Valor Seleccionado por defecto
        cargaDatosValor();
    }

    //Carga los Datos del Valor Seleccionado
    private void cargaDatosValor(){        
        //Recuperamos los Datos del Valor Seleccionado
        datosValor = getDatosValor();

        //Reiniciamos el JTable si este ya tiene algun contenido
        cargaJTableDatosValor();
    }
    
    //Reinicia el Modelo del Combo
    private void reiniciaCombo(javax.swing.JComboBox combo){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        combo.setModel(modelo);
    }
    
    //Actualiza los Datos de los Valores de la Web MegaBolsa
    public void actualizaDatosMegaBolsa(){

        //Obtenemos los Datos de los Valores de la Web de MegaBolsa
        datosMegaBolsa = new MegaBolsa(con, urlDatosFichero, comodinFichero, extensionFichero, codCategoria);

        //Actualizamos los Datos de los Valores de la Web de MegaBolsa (Tarea en Paralelo)
        getDatosValores().start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCBCategorias = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jCBValores = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatosValor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Categoria:");

        jCBCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBCategoriasItemStateChanged(evt);
            }
        });

        jLabel2.setText("Valores:");

        jCBValores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBValoresItemStateChanged(evt);
            }
        });

        jTableDatosValor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableDatosValor);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Históricos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBValores, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCBCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jCBValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Cerramos la Conexion
        entityManager.close();
    }//GEN-LAST:event_formWindowClosing

    private void jCBCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBCategoriasItemStateChanged
        //Una vez Desmarcado el Valor Seleccionado
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED){
            //Cargamos los Valores Asociados a la Categoria Seleccionada
            cargaValoresCategoria();
        }
    }//GEN-LAST:event_jCBCategoriasItemStateChanged

    private void jCBValoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBValoresItemStateChanged
        //Una vez Desmarcado el Valor Seleccionado
        if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED){
            //Cargamos los Datos Asociados al Valor Seleccionado
            cargaDatosValor();
        }
    }//GEN-LAST:event_jCBValoresItemStateChanged

    //Recupera los Valores de la Categoria Seleccionada
    private List<Valor> getValoresCategoria(){
        //Posicion de la Categoria Seleccionada
        int posIndCat = jCBCategorias.getSelectedIndex();
        
        //Retornamos la Lista de Valores de la Categoria Seleccionada
        return (new ArrayList(categorias.getCategoriaList().get(posIndCat).getValorCollection()));
    }

    //Recupera el Valor Seleccionado de la Categoria Seleccionada
    private Valor getValorCategoria(){
        //Posicion de la Categoria Seleccionada
        int posIndCat = jCBCategorias.getSelectedIndex();
        
        //Posicion del Valor Seleccionado
        int posIndVal = jCBValores.getSelectedIndex();

        //Recupera los Valores de la Categoria Seleccionada (Pasamos de un Collection a un List)
        List<Valor> valores = new ArrayList(categorias.getCategoriaList().get(posIndCat).getValorCollection());
        
        //Retornamos de la Lista Solo el Valor Seleccionado
        return valores.get(posIndVal);
    }

    //Recupera los Datos del Valor Seleccionado
    private DatosValor getDatosValor(){
        //Posicion de la Categoria Seleccionada
        int posIndCat = jCBCategorias.getSelectedIndex();
        
        //Posicion del Valor Seleccionado
        int posIndVal = jCBValores.getSelectedIndex();

        //Recupera los Valores de la Categoria Seleccionada (Pasamos de un Collection a un List)
        List<Valor> valores = new ArrayList(categorias.getCategoriaList().get(posIndCat).getValorCollection());
        
        //Recupera los Datos del Valor Seleccionado
        DatosValor datosValor = new DatosValor();
        datosValor.setDatoValorList(new ArrayList(valores.get(posIndVal).getDatoValorCollection()));

        //Retornamos los Datos del Valor Seleccionado
        return datosValor;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCBCategorias;
    private javax.swing.JComboBox jCBValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDatosValor;
    // End of variables declaration//GEN-END:variables
}
