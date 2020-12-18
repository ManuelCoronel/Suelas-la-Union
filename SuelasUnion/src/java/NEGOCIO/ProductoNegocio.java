/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.ProductoJpaController;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class ProductoNegocio {

    public static int obtenerUltimoIdProducto() {
        ProductoJpaController productoDAO = new DAO.ProductoJpaController();
        List<DTO.Producto> productos = productoDAO.findProductoEntities();
        return productos.get(productos.size() - 1).getId();
    }

}
