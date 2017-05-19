package com.apmv1.webservices;

/**
 * Created by Beto on 10/07/2015.
 */
public class Constantes {

    /**
     * Transicion Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transicion Detalle -> Actualizacion
     */
    public static final int CODIGO_ACTUALIZACION = 101;


    /**
     * URLs del Web Service
     */
    public static final String GET = "http://10.0.2.2/py_webservices/obtener_metas.php";
    public static final String GET_BY_ID = "http://10.0.2.2/py_webservices/obtener_meta_por_id.php";
    public static final String UPDATE = "http://10.0.2.2/py_webservices/actualizar_meta.php";
    public static final String DELETE = "http://10.0.2.2/py_webservices/borrar_meta.php";
    public static final String INSERT = "http://10.0.2.2/py_webservices/insertar_meta.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";
}
