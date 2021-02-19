package edu.co.escuelaing.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionJDBCBaseDeDatos {
    private static Connection conexion;

    public static void main(String args[]) {
        Statement stmt = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexion = DriverManager.getConnection("jdbc:postgres://lmdsycuavxahau:e8e14a60753485abd455821df65bbaad85a7ac42ea715c84957bff640a5bf49f@ec2-54-237-143-127.compute-1.amazonaws.com:5432/d2jllscpeuch14", "lmdsycuavxahau", "e8e14a60753485abd455821df65bbaad85a7ac42ea715c84957bff640a5bf49f");
            conexion.setAutoCommit(false);
            stmt = conexion.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            conexion.commit();
            conexion.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    /**
     * Retorna la conexion a la base de datos.
     * @return - conexion.
     */
    public static Connection getConnection() {
        return conexion;
    }

}
