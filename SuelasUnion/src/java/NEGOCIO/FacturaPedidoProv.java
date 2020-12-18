/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DTO.ProductoPedidoProveedor;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class FacturaPedidoProv {

    public static double calcularFacturaProv(DTO.PedidoProveedor pedidoProv) {

        DAO.ProductoPedidoProveedorJpaController pedidosDAO = new DAO.ProductoPedidoProveedorJpaController();

        List<DTO.ProductoPedidoProveedor> pedidos = pedidosDAO.findProductoPedidoProveedorEntities();
        double total = 0;
        for (ProductoPedidoProveedor pedido : pedidos) {
            if (pedido.getPedidoProveedor().getId() == pedidoProv.getId()) {
                total += pedido.getTotal();
            }
        }
        return total;
    }

}
