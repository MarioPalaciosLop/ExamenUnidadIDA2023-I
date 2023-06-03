// Importaciones necesarias
package negocio;

import dominio.Propiedad;
import datos.PropiedadDAO;
import datos.impl.PropiedadDAOImpl;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class PropiedadControl {

    private final PropiedadDAO DATOS;
    private Propiedad pro;
    private DefaultTableModel modeloTabla;

    public PropiedadControl() {
        this.DATOS = new PropiedadDAOImpl();
        this.pro = new Propiedad();
    }

    public DefaultTableModel listar(String texto) {
        List<Propiedad> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        //Establecemos la columna del tableModel
        String[] titulos = {"ID", "NOMBRE","DIRECCION","CARACTERISTICAS","ESTADO","SALARIO"};
        //Declaramos un vector que será el que agreguemos como registro al DefaultTableModel
        String[] registro = new String[6];
        //agrego los títulos al DefaultTableModel
        this.modeloTabla = new DefaultTableModel(null, titulos);
        //Recorrer toda mi lista y la pasare al DefaultTableModel
        for (Propiedad item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDireccion();
            registro[3] = item.getCaracteristicas();
            registro[4] = item.getEstado();
            registro[5] = Double.toString(item.getPrecioalquiler());
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
    }

    public String insertar(Propiedad obj) {
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }

    public String actualizar(Propiedad obj) {
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización de datos.";
        }
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }

}
