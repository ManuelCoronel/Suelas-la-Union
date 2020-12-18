/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import DAO.ClienteJpaController;
import DTO.Cliente;

/**
 *
 * @author MANUEL
 */
public class TestCliente {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        ClienteJpaController dao = new ClienteJpaController();
        cliente = dao.findCliente("13476800");
        System.out.println(cliente.getCorreo());
    }
}
