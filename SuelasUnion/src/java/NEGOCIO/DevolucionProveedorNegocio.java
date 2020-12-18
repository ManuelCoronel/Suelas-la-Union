/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.DevolucionProveedorJpaController;
import DTO.DevolucionProveedor;
import java.util.ArrayList;
import java.util.List;

public class DevolucionProveedorNegocio {

    public static List<DevolucionProveedor> busquePorParametros(String id) {
        DevolucionProveedorJpaController devolucionDAO = new DevolucionProveedorJpaController();
        List<DevolucionProveedor> devolucion = (List<DevolucionProveedor>) devolucionDAO.findDevolucionProveedorEntities();
        List<DevolucionProveedor> devoluciones = new ArrayList<>();
        if (id == null || id.equalsIgnoreCase("")) {
            return devolucion;
        } else {
            for (DevolucionProveedor proveedor : devolucion) {
                String identificador = Integer.toString(proveedor.getId());
                if (identificador.equalsIgnoreCase(id)) {
                    devoluciones.add(proveedor);
                }
            }
        }
        return devoluciones;
    }
}
