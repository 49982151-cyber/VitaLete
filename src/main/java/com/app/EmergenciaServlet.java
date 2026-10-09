package com.app;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


// Nota: Tomcat 10.x utiliza el paquete 'jakarta.servlet'
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("EmergenciaServlet")
public class EmergenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombreFamiliar = "Familiar (Hija)";
        String telefonoFamiliar = "5491112345678";
        String tipoContacto = "whatsapp";
        String enlaceFamiliar = "";

        String sql = "SELECT nombre_contacto, telefono_contacto, preferencia_contacto FROM adultos_mayores LIMIT 1";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                if (rs.getString("nombre_contacto") != null) {
                    nombreFamiliar = rs.getString("nombre_contacto");
                }
                if (rs.getString("telefono_contacto") != null) {
                    telefonoFamiliar = rs.getString("telefono_contacto");
                }
                if (rs.getString("preferencia_contacto") != null) {
                    tipoContacto = rs.getString("preferencia_contacto");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Armar la URL de contacto dinámicamente según la preferencia
        if ("whatsapp".equalsIgnoreCase(tipoContacto)) {
            String mensaje = "¡Hola! Necesito ayuda urgente, por favor comunícate conmigo.";
            String mensajeCodificado = URLEncoder.encode(mensaje, StandardCharsets.UTF_8.toString());
            enlaceFamiliar = "https://wa.me/" + telefonoFamiliar + "?text=" + mensajeCodificado;
        } else {
            enlaceFamiliar = "tel:" + telefonoFamiliar;
        }

        // 4. Enviar los datos preparados a la vista JSP
        request.setAttribute("nombreFamiliar", nombreFamiliar);
        request.setAttribute("enlaceFamiliar", enlaceFamiliar);

        // 5. Redirigir la petición a la vista
        request.getRequestDispatcher("/vistas/vista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}