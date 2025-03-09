package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe per gestionar la connexió a la base de dades.
 */
public class DatabaseConnection {
    // URL de la base de dades, usuari i contrasenya
    private static final String URL = "jdbc:mariadb://192.168.0.201:3306/la_meva_llibreria"; 
    private static final String USER = "cristofol";
    private static final String PASSWORD = "cristofol"; 

    // Objecte Connection
    private Connection connection;

    /**
     * Constructor que estableix la connexió amb la base de dades.
     *
     * @throws SQLException Si ocorre un error en connectar-se a la base de dades.
     */
    public DatabaseConnection() throws SQLException {
        try {
            // Carregar el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Crear la connexió
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexió establerta amb la base de dades.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No s'ha trobat el driver de MariaDB.", e);
        }
    }

    /**
     * Retorna l'objecte de connexió actual.
     * 
     * @return Connexió actual a la base de dades.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Crea i retorna una nova instància de connexió a la base de dades.
     * 
     * @return Nova connexió a la base de dades.
     * @throws SQLException Si ocorre un error en connectar-se.
     */
    public static Connection getConnectionInstance() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        return dbConnection.getConnection();
    }

    /**
     * Tanca la connexió actual si està oberta.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexió tancada.");
            } catch (SQLException e) {
                System.err.println("Error en tancar la connexió: " + e.getMessage());
            }
        }
    }

    /**
     * Mètode principal per provar la connexió a la base de dades.
     *
     * @param args Arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        try {
            // Crear una instància de la connexió
            DatabaseConnection dbConnection = new DatabaseConnection();
            // Usar la connexió (en aquest cas, només provar-la)
            dbConnection.getConnection();

            // Tanca la connexió
            dbConnection.closeConnection();
        } catch (SQLException e) {
            System.err.println("Error en connectar-se a la base de dades: " + e.getMessage());
        }
    }
}
