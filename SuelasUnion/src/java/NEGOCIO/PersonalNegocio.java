/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.PersonalJpaController;
import DTO.Personal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class PersonalNegocio {

    public static List<Personal> busquePorParametros(String nombre) {
        PersonalJpaController personalDAO = new PersonalJpaController();
        List<Personal> personas = (List<Personal>) personalDAO.findPersonalEntities();
        List<Personal> personasRta = new ArrayList<>();
        if (nombre == null || nombre.equalsIgnoreCase("")) {
            return personas;
        } else {
            for (Personal persona : personas) {
                String nom = persona.getNombre();
                if (nom.equalsIgnoreCase(nombre)) {
                    personasRta.add(persona);
                }
            }
        }
        return personasRta;
    }
}
