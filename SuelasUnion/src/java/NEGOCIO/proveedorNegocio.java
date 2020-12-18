/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.ProveedorJpaController;
import DTO.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAMILA
 */
public class proveedorNegocio {

    public static List<Proveedor> busquePorParametros(String cedula) {
        ProveedorJpaController devolucionDAO = new ProveedorJpaController();
        List<Proveedor> proveedor1 = (List<Proveedor>) devolucionDAO.findProveedorEntities();
        List<Proveedor> proveedor2 = new ArrayList<>();
        if (cedula == null || cedula.equalsIgnoreCase("")) {
            return proveedor1;
        } else {
            for (Proveedor proveedor : proveedor1) {
                String identificador = proveedor.getCedula();
                if (identificador.equalsIgnoreCase(cedula)) {
                    proveedor2.add(proveedor);
                }
            }
        }
        return proveedor2;
    }
}
