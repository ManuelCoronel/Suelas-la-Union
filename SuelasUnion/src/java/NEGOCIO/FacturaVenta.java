/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DTO.ProductoPedidoVenta;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class FacturaVenta {

    public static double calcularFactura(int id) {
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        List<DTO.ProductoPedidoVenta> pedidos = (List<DTO.ProductoPedidoVenta>) pedidoDAO.findPedidoDeVenta(id).getProductoPedidoVentaList();
        double total = 0;
        for (ProductoPedidoVenta pedido : pedidos) {
            total += pedido.getTotal();
        }
        return total;
    }

}
