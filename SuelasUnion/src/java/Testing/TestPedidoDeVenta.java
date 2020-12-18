/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.ClienteJpaController;
import DAO.Conexion;
import DAO.EstadoJpaController;
import DAO.PedidoDeVentaJpaController;
import DAO.PersonalJpaController;
import DAO.TipoEntregaJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Cliente;
import DTO.PedidoDeVenta;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MANUEL
 */
public class TestPedidoDeVenta {

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException, Exception {
        Conexion con = Conexion.getConexion();
        PedidoDeVentaJpaController pedidoDeVentaDAO = new PedidoDeVentaJpaController();
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione una opcion");
        System.out.println("1. Consultar los pedidos");
        System.out.println("2. Insertar un pedido");
        System.out.println("3.Eliminar");
        System.out.println("4. ACtualizar");
        int opcion = sc.nextInt();
        if (opcion == 1) {
            List<PedidoDeVenta> pedidos = pedidoDeVentaDAO.findPedidoDeVentaEntities();
            for (PedidoDeVenta pedido : pedidos) {
                System.out.println("id : " + pedido.getId() + " Fecha orden:" + pedido.getFechaOrden() + " Total" + pedido.getTotal() + " Cedula cliente:" + pedido.getClienteCedula());
            }
        }
        if (opcion == 2) {
            Date utilDate = new Date((100), 0, 1);
            long lnMilisegundos = utilDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            ClienteJpaController ClienteDAO = new ClienteJpaController();
            Cliente cliente = ClienteDAO.findCliente("13476800");
            PedidoDeVenta pedido = new PedidoDeVenta();
            pedido.setClienteCedula(cliente);
            pedido.setFechaEntregaFactura(sqlDate);
            pedido.setFechaOrden(new Date(1000, 12, 3));
            pedido.setFechaPlazo(new Date(2020, 12, 3));
            EstadoJpaController estadoDAO = new EstadoJpaController();
            pedido.setEstadoId(estadoDAO.findEstado(1));
            PersonalJpaController personalDAO = new PersonalJpaController();
            pedido.setPersonalId(personalDAO.findPersonal(1));
            TipoEntregaJpaController TipoEntregaDAO = new TipoEntregaJpaController();
            pedido.setTipoEntregaId(TipoEntregaDAO.findTipoEntrega(1));
            pedidoDeVentaDAO.create(pedido);
        }
        if (opcion == 3) {
            System.out.println("Ingrese el id que quiere eliminar");
            int idE = sc.nextInt();
            pedidoDeVentaDAO.destroy(idE);
        }
        if (opcion == 4) {
            PedidoDeVenta pedidoEditar = pedidoDeVentaDAO.findPedidoDeVenta(15);
            TipoEntregaJpaController TipoEntregaDAO = new TipoEntregaJpaController();
            pedidoEditar.setTipoEntregaId(TipoEntregaDAO.findTipoEntrega(2));
            pedidoDeVentaDAO.edit(pedidoEditar);
        }
    }
}
