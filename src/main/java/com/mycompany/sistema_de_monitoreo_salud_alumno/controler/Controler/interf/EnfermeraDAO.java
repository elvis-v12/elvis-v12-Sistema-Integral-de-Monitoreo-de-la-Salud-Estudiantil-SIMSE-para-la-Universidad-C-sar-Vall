package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Enfermera;
import java.util.List;

public interface EnfermeraDAO {
    void agregarEnfermera(Enfermera enfermera);
    void actualizarEnfermera(Enfermera enfermera);
    void eliminarEnfermera(int idEnfermera);
    Enfermera obtenerEnfermeraPorId(int idEnfermera);
    List<Enfermera> obtenerTodasLasEnfermeras();
}
