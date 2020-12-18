/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.TiraJpaController;
import DTO.Tira;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class TirasNegocio {

    public static List<Tira> busquePorParametros(String modelo, String anchura) {
        TiraJpaController tiraDAO = new TiraJpaController();
        List<Tira> tiras = tiraDAO.findTiraEntities();
        List<Tira> tiras2 = new ArrayList<>();
        if (modelo == null && anchura == null || modelo.equalsIgnoreCase("") && anchura.equalsIgnoreCase("")) {
            return tiras;
        }
        if ((modelo == null && anchura != null) || modelo.equalsIgnoreCase("") && !anchura.equalsIgnoreCase("")) {
            for (Tira tira : tiras) {
                if (tira.getAnchura().equalsIgnoreCase(anchura)) {
                    tiras2.add(tira);
                }
            }
        }
        if ((modelo != null && anchura == null) || (!modelo.equalsIgnoreCase("") && anchura.equalsIgnoreCase(""))) {
            for (Tira tira : tiras) {
                if (tira.getModelo().equalsIgnoreCase(modelo)) {
                    tiras2.add(tira);
                }
            }
        }
        if ((modelo != null && anchura != null) || (!modelo.equalsIgnoreCase("") && !anchura.equalsIgnoreCase(""))) {
            for (Tira tira : tiras) {
                if (tira.getModelo().equalsIgnoreCase(modelo) && tira.getAnchura().equalsIgnoreCase(anchura)) {
                    tiras2.add(tira);
                }
            }
        }
        return tiras2;
    }

    static public boolean existeTira(String modelo, String anchura, int color) {
        TiraJpaController tiraDAO = new TiraJpaController();
        List<Tira> tiras = tiraDAO.findTiraEntities();
        for (Tira tira : tiras) {
            if (modelo.equalsIgnoreCase(tira.getModelo()) && anchura.equalsIgnoreCase(tira.getAnchura()) && color == tira.getColorId().getId()) {
                return true;
            }
        }
        return false;
    }

    static public int obtenerTiraId(String modelo, String anchura, int color) {
        TiraJpaController tiraDAO = new TiraJpaController();
        List<Tira> tiras = tiraDAO.findTiraEntities();
        for (Tira tira : tiras) {
            if (modelo.equalsIgnoreCase(tira.getModelo()) && anchura.equalsIgnoreCase(tira.getAnchura()) && color == tira.getColorId().getId()) {
                return tira.getProducto().getId();
            }
        }
        return -1;
    }

}
