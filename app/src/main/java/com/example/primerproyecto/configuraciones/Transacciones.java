package com.example.primerproyecto.configuraciones;

public class Transacciones
{
    //Nombre de la base de datos
    public static final String NameDatabase = "PM01DB";

    //Creación de las tablas de la base de datos
    public static final String tablaEmpleados = "empleados";
    /*
        Campos especificos de la tabla empleados
    */
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    /* Transacciones DDL (Data Definition Lenguage) */
    public static final String CreateTableEmpleados = "CREATE TABLE "+tablaEmpleados
            +" ("+id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +nombres+" TEXT,"
            +apellidos+" TEXT,"
            +edad+" INTEGER,"
            +correo+" TEXT)";

    public static final String DropTableEmpleados = "DROP TABLE IF EXISTS "+tablaEmpleados;
}
