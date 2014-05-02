package Servlets;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
private Connection conexion;

 public static void main(String[] args) {
  Conection obconeccion=new Conection();
    obconeccion.Conectar();
    }
    
    public Connection getConexion() {
        return conexion;
    }

        public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public Conection Conectar()
    {
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:db1";
        conexion= DriverManager.getConnection(BaseDeDatos,"ADMIN","admin");
        if(conexion!=null)
        {
        System.out.println("Conexion exitosa a esquema ADMIN");
        }
        else{System.out.println("Conexion fallida");}
        }
        catch(Exception e)
        {e.printStackTrace();}
       
    return this;
    }

}
