<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VitaLete - Botones de Emergencia</title>
    <style>
        .panel-emergencia {
            display: flex;
            flex-direction: column;
            gap: 15px;
            max-width: 350px;
            margin: 50px auto;
            font-family: Arial, sans-serif;
        }
        .btn-emergencia {
            display: block;
            padding: 18px 20px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
            color: #ffffff;
            text-decoration: none;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            transition: transform 0.2s;
        }
        .btn-emergencia:hover {
            transform: scale(1.03);
        }
        .btn-911 {
            background-color: #d9534f; /* Rojo de auxilio */
        }
        .btn-familiar {
            background-color: #25D366; /* Verde WhatsApp / Teléfono */
        }
    </style>
</head>
<body>

<div class="panel-emergencia">
    <!-- Botón 1: Llamada directa de auxilio al 911 -->
    <a href="tel:911" class="btn-emergencia btn-911">
        🚨 Auxilio 911
    </a>

    <!-- Botón 2: Contacto dinámico con el familiar recuperado de la BD -->
    <a href="${enlaceFamiliar}" target="_blank" class="btn-emergencia btn-familiar">
        📲 Contactar a ${nombreFamiliar}
    </a>
</div>

</body>
</html>