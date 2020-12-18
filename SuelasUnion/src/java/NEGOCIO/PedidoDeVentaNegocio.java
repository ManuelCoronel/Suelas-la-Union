/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DTO.Cliente;
import DTO.PedidoDeVenta;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class PedidoDeVentaNegocio {

    public static List<DTO.PedidoDeVenta> busquedaPedidosPorParametros(String cedula, int estado) {
        DAO.ClienteJpaController clienteDAO = new DAO.ClienteJpaController();
        DAO.PedidoDeVentaJpaController pedidosDAO = new DAO.PedidoDeVentaJpaController();
        List<DTO.PedidoDeVenta> pedidos;
        if (cedula == null || cedula.equalsIgnoreCase("")) {
            pedidos = pedidosDAO.findPedidoDeVentaEntities();
        } else {
            pedidos = clienteDAO.findCliente(cedula).getPedidoDeVentaList();
        }
        if (estado == 0) {
            return pedidos;
        }
        Iterator<DTO.PedidoDeVenta> i = pedidos.iterator();
        while (i.hasNext()) {
            DTO.PedidoDeVenta s = i.next();
            if (s.getEstadoId().getId() != estado) {
                i.remove();
            }
        }
        return pedidos;
    }

    public static List<PedidoDeVenta> busquedaPedidosPorParametrosII(Cliente cliente, int estado) {
        DAO.ClienteJpaController clienteDAO = new DAO.ClienteJpaController();
        cliente = clienteDAO.findCliente(cliente.getCedula());
        List<PedidoDeVenta> pedidos = cliente.getPedidoDeVentaList();
        if (estado == 0) {
            return pedidos;
        } else {
            Iterator<PedidoDeVenta> i = pedidos.iterator();
            while (i.hasNext()) {
                PedidoDeVenta s = i.next();
                if (s.getEstadoId().getId() != estado) {
                    i.remove();
                }
            }
        }
        return pedidos;
    }

}
