package edu.co.escuelaing.Persistencia;

import edu.co.escuelaing.entidades.Busqueda;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ConexionJDBCBaseDeDatos {

    @Test
    public void canMakeConnection(){
        Boolean connectionState = false;
        ConexionJDBCBaseDeDatos dataBaseConnectionJDBC;
        dataBaseConnectionJDBC = new ConexionJDBCBaseDeDatos();
        connectionState = dataBaseConnectionJDBC != null;
        assertTrue(connectionState);
    }


    @Test
    public void canSelectData() throws SQLException {
        ConexionJDBCBaseDeDatos dataBaseJDBC = new ConexionJDBCBaseDeDatos();
        String result = dataBaseJDBC.toString();
        if(result == null || result.isEmpty()){
            fail("Datos mal consultados!");
        }
    }

}