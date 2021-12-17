/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuda
 */
public class ConnexionBD {

    private static Connection conn;
    private ResultSet rs;
    //  private static ConnexionBD instanciaUnica;

    /* public static ConnexionBD getInstance(){
        if (instanciaUnica==null){
            instanciaUnica=new ConnexionBD();
        }
        return instanciaUnica;
    }*/
    public ConnexionBD() {
        try {
            //obtenemos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/areopuerto", "root", "");

            if (conn != null) {
                System.out.println(">>>>>>>>>>> La conexión a la Base de Datos se ha creado con éxito");
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(">>>>>>>>>>> No se puede conectar con la base de datos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Hace una consulta a la base de datos y se guarda en el ResultSet
     *
     * @param query Hacer una consulta a la base de datos
     */
    public void cargaDatos(String query) {
        Statement stmt = null;
        try {
            stmt = (Statement) conn.createStatement();
            rs = (ResultSet) stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Muestra los datos de la tabla pasajeros
     */
    public void mostrarPasajeros() {
        this.cargaDatos("SELECT * FROM pasajeros");
        System.out.println("PASAJERO  VUELO  PLAZA  FUMADOR");
        try {
            while (rs.next()) {
                String codigo = Integer.toString(rs.getInt("NUMERO"));
                String codigoVuelo = rs.getString("CODIGO_VUELO");
                String plaza = rs.getString("TIPO_PLAZA");
                boolean fumador = rs.getBoolean("fumador");

                System.out.println(codigo + "   " + codigoVuelo + "   " + plaza + "   " + fumador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("****************************************************");
    }

    /**
     * Muestra los datos de la tabla pasajeros
     */
    public void mostrarVuelos() {
        this.cargaDatos("SELECT * FROM vuelos");
        System.out.println("CODIGO      FECHA-HORA      PROCEDENCIA  DESTINO");
        try {
            while (rs.next()) {
                String codigoVuelo = rs.getString("COD_VUELO");
                String fecha = rs.getString("HORA_SALIDA");
                String procedencia = rs.getString("PROCEDENCIA");
                String destino = rs.getString("DESTINO");

                System.out.println(codigoVuelo + "   " + fecha + "   " + procedencia + "    " + destino);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("****************************************************");
    }

    public void mostrarPasajerosVuelo(String codigo) {
        this.cargaDatos("SELECT * FROM pasajeros WHERE  codigo_vuelo='" + codigo + "'");
        System.out.println("PASAJERO  VUELO  PLAZA  FUMADOR");
        try {
            while (rs.next()) {
                String numero = Integer.toString(rs.getInt("NUMERO"));
                String codigoVuelo = rs.getString("CODIGO_VUELO");
                String plaza = rs.getString("TIPO_PLAZA");
                boolean fumador = rs.getBoolean("fumador");

                System.out.println(numero + "   " + codigoVuelo + "   " + plaza + "   " + fumador);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("****************************************************");
    }

    public void insertarVuelo(String codigo, Date hora, String destino, String procede, int pF, int pNof, int pT, int pP) {
        PreparedStatement stmt = null;
        Timestamp timestamp = new Timestamp(hora.getTime());

        String insertDatosQL = "INSERT INTO vuelos VALUES "
                + "(?,?,?,?,?,?,?,?)";
        try {
            // preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            stmt = conn.prepareStatement(insertDatosQL);
            
            stmt.setString(1, codigo);
            stmt.setTimestamp(2,timestamp);
            stmt.setString(3, destino);
            stmt.setString(4, procede);            
            stmt.setInt(5, pF);
            stmt.setInt(6, pNof);
            stmt.setInt(7, pT);
            stmt.setInt(8, pP);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
