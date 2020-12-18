/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.DevolucionClienteJpaController;
import DTO.DevolucionCliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DevolucionNegocio {

    public static List<DevolucionCliente> busquePorParametros(String id) {
        DevolucionClienteJpaController devolucionClienteDAO = new DevolucionClienteJpaController();
        List<DevolucionCliente> devoluciones = (List<DevolucionCliente>) devolucionClienteDAO.findDevolucionClienteEntities();
        List<DevolucionCliente> devolucionesRta = new ArrayList<>();
        if (id == null || id.equalsIgnoreCase("")) {
            return devoluciones;
        } else {
            for (DevolucionCliente devolucion : devoluciones) {
                String identificador = Integer.toString(devolucion.getId());
                if (identificador.equalsIgnoreCase(id)) {
                    devolucionesRta.add(devolucion);
                }
            }
        }
        return devolucionesRta;
    }
}
