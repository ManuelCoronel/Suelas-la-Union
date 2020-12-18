/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.PlantillaJpaController;
import DTO.Plantilla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAMILA
 */
public class PlantillaNegocio {

    static PlantillaJpaController plantillaDAO;
    static List<Plantilla> listaPlantilla;

    public static List<Plantilla> buscandoPlantilla(String modelo, String talla) {
        plantillaDAO = new PlantillaJpaController();
        List<Plantilla> listaPlantilla2 = new ArrayList<>();
        listaPlantilla = plantillaDAO.findPlantillaEntities();
        if ((modelo == null && talla == null) || (modelo.equalsIgnoreCase("") && talla.equalsIgnoreCase(""))) {
            return listaPlantilla;
        }
        if ((modelo == null && talla != null) || (modelo.equalsIgnoreCase("") && !talla.equalsIgnoreCase(""))) {
            for (Plantilla p : listaPlantilla) {
                if (p.getTalla().equalsIgnoreCase(talla)) {
                    listaPlantilla2.add(p);
                }
            }
        }
        if ((modelo != null && talla == null) || !modelo.equalsIgnoreCase("") && talla.equalsIgnoreCase("")) {
            for (Plantilla p : listaPlantilla) {
                if (p.getModelo().equalsIgnoreCase(modelo)) {
                    listaPlantilla2.add(p);
                }
            }
        }
        if ((modelo != null && talla != null) || (!modelo.equalsIgnoreCase("") && !talla.equalsIgnoreCase(""))) {
            for (Plantilla p : listaPlantilla) {
                if (p.getModelo().equalsIgnoreCase(modelo) && p.getTalla().equalsIgnoreCase(talla)) {
                    listaPlantilla2.add(p);
                }
            }
        }
        return listaPlantilla2;
    }

    public static boolean existePlantilla(String modelo, String talla) {
        boolean rta = false;
        plantillaDAO = new PlantillaJpaController();
        listaPlantilla = plantillaDAO.findPlantillaEntities();
        for (Plantilla plantilla : listaPlantilla) {
            if (plantilla.getModelo().equalsIgnoreCase(modelo) && plantilla.getTalla().equalsIgnoreCase(talla)) {
                return true;
            }
        }
        return rta;
    }

    public static int obtenerIdPlantilla(String modelo, String talla) {
        plantillaDAO = new PlantillaJpaController();
        listaPlantilla = plantillaDAO.findPlantillaEntities();
        for (Plantilla plantilla : listaPlantilla) {
            if (plantilla.getModelo().equalsIgnoreCase(modelo) && plantilla.getTalla().equalsIgnoreCase(talla)) {
                return plantilla.getProductoId();
            }
        }
        return -1;
    }

}
