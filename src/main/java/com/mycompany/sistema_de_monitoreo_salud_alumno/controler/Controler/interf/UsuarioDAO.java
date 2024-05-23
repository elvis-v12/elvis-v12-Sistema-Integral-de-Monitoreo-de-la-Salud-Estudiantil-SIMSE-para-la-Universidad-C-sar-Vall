
package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;

import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Usuario;

public interface UsuarioDAO {
    Usuario autenticarUsuario(String codigo, String contrasena);
    Usuario obtenerUsuarioPorId(int idUsuario); 
}
