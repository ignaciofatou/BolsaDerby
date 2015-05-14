package bolsaderby.model;

import javax.swing.table.AbstractTableModel;
import bolsaderby.data.DatoValor;
import bolsaderby.data.DatosValor;

public class DatosValorTableModel extends AbstractTableModel {

    private DatosValor datosValor;

    public DatosValorTableModel(DatosValor datosValor) {
        this.datosValor = datosValor;
    }
    
    @Override
    public int getRowCount() {
        return datosValor.getDatoValorList().size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DatoValor datoValor = datosValor.getDatoValorList().get(rowIndex);
        switch(columnIndex) {
            case 0:                
                return datoValor.getApertura() + ", " + datoValor.getApertura() + ", " + datoValor.getDatoValorPK().getFecha().toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Valor";
            case 1:
                return "Fecha";
            case 2:
                return "Apertura";
            case 3:
                return "Maximo";
            case 4:
                return "Minimo";
            case 5:
                return "Cierre";
            case 6:
                return "Volumen";
            default:
                return null;
        }
    }    
    
}
