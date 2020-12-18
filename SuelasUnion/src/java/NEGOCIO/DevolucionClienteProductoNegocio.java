/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.DevolucionClienteJpaController;
import DAO.DevolucionClienteProductoJpaController;
import DTO.DevolucionCliente;
import DTO.DevolucionClienteProducto;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DevolucionClienteProductoNegocio {

    public static List<DevolucionClienteProducto> busquePorParametros(String id) {
        DevolucionClienteJpaController devcliente = new DevolucionClienteJpaController();
        DevolucionClienteProductoJpaController devolucionClienteProductoDAO = new DevolucionClienteProductoJpaController();
        List<DevolucionClienteProducto> devoluciones = (List<DevolucionClienteProducto>) devolucionClienteProductoDAO.findDevolucionClienteProductoEntities();
        List<DevolucionClienteProducto> devolucionesRta = (List<DevolucionClienteProducto>) devcliente.findDevolucionCliente(Integer.parseInt(id)).getDevolucionClienteProductoList();
        if (id == null) {
            return devoluciones;
        } else {
            for (DevolucionClienteProducto devolucion : devoluciones) {
                DevolucionCliente identificador = devolucion.getDevolucionCliente();
                if (identificador.equals(id)) {
                    devolucionesRta.add(devolucion);
                }
            }
        }
        return devolucionesRta;
    }
}
