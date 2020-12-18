/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.SuelaJpaController;
import DTO.Suela;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author MANUEL
 */
public class Suelas {

    public static List<Suela> obtenerSuelasPorTipo(String tipo) {
        SuelaJpaController suelasDAO = new SuelaJpaController();
        List<Suela> suelas = suelasDAO.findSuelaEntities();
        List<Suela> suelasRta = new ArrayList<Suela>();
        for (Suela suelaDTO : suelas) {
            if (suelaDTO.getTipoSuela().equalsIgnoreCase(tipo)) {
                suelasRta.add(suelaDTO);
            }
        }
        return suelasRta;
    }

    public static List<Suela> busquedaPorParametros(List<Suela> suelasFiltro, String modelo, String talla, String color) {
        ArrayList<Suela> suelasFiltro2 = (ArrayList<Suela>) suelasFiltro;
        if ((modelo == null && color == null && talla == null) || (modelo.equalsIgnoreCase("") && color.equalsIgnoreCase("") && talla.equalsIgnoreCase(""))) {
            return suelasFiltro;
        }
        Iterator<Suela> i = suelasFiltro.iterator();
        if (!(modelo == null || modelo.equalsIgnoreCase(""))) {
            while (i.hasNext()) {
                Suela s = i.next();
                if (!s.getModelo().equalsIgnoreCase(modelo)) {
                    i.remove();
                }
            }
        }
        if (!(talla == null || talla.equalsIgnoreCase(""))) {
            while (i.hasNext()) {
                Suela s = i.next();
                if (!s.getTalla().equalsIgnoreCase(talla)) {
                    i.remove();
                }
            }
        }
        if (!(color == null || color.equalsIgnoreCase(""))) {
            while (i.hasNext()) {
                Suela s = i.next();
                if (!s.getColorId().getColor().equalsIgnoreCase(color)) {
                    i.remove();
                }
            }
        }
        return suelasFiltro;
    }

    static public boolean existeSuela(String modelo, String tipoSuela, String talla, int color) {
        SuelaJpaController suelasDAO = new SuelaJpaController();
        List<Suela> suelas = suelasDAO.findSuelaEntities();
        for (Suela suela : suelas) {
            if (modelo.equalsIgnoreCase(suela.getModelo()) && tipoSuela.equalsIgnoreCase(suela.getTipoSuela()) && talla.equalsIgnoreCase(talla) && color == suela.getColorId().getId()) {
                return true;
            }
        }
        return false;
    }

    static public int obtenerSuelaId(String modelo, String tipoSuela, String talla, int color) {
        SuelaJpaController suelasDAO = new SuelaJpaController();
        List<Suela> suelas = suelasDAO.findSuelaEntities();
        for (Suela suela : suelas) {
            if (modelo.equalsIgnoreCase(suela.getModelo()) && tipoSuela.equalsIgnoreCase(suela.getTipoSuela()) && talla.equalsIgnoreCase(talla) && color == suela.getColorId().getId()) {
                return suela.getProductoId();
            }
        }
        return -1;
    }
}
