/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.SuelaJpaController;
import DTO.Suela;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class SuelasPVC {

    public static int calcularCantidadSuelasPVC() {
        SuelaJpaController suelasDAO = new SuelaJpaController();
        List<Suela> suelas = suelasDAO.findSuelaEntities();
        int cantidadSuelasEx = 0;
        for (Suela suela : suelas) {
            if (suela.getTipoSuela().equals("2")) {
                cantidadSuelasEx++;
            }
        }
        return cantidadSuelasEx;
    }

}
