package mx.edu.ittepic.lab_prueba.utilidades;

public class Utilidades {
    public static String TABLA="usuario";
    public static String CLAVE="clave";
    public static String NOMBRE="nombre";
    public static String SUELDO="sueldo";
    public static String EMAIL="email";
    public static String FECHA="fecha";
    public static String ID="_id";

    public static final String DBNAME="usuariosLAB";


    public static String CREAR_TABLA="CREATE TABLE "+TABLA+"("+
            ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CLAVE+" TEXT NOT NULL, "+
            NOMBRE+" TEXT, " +
            SUELDO+" TEXT," +
            EMAIL+" TEXT," +
            FECHA+" TEXT )";


}
