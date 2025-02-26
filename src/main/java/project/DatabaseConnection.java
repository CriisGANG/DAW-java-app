package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para manejar la conexión a la base de datos.
 */
public class DatabaseConnection {
    // URL de la base de datos, usuario y contraseña
    private static final String URL = "jdbc:mariadb://192.168.0.201:3306/la_meva_llibreria"; 
    private static final String USER = "cristofol";
    private static final String PASSWORD = "cristofol"; 

    // Objeto Connection
    private Connection connection;

    /**
     * Constructor: Inicializa la conexión.
     *
     * @throws SQLException si ocurre un error al conectar con la base de datos
     */
    public DatabaseConnection() throws SQLException {
        try {
            // Cargar el driver de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Crear la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con la base de datos.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se encontró el driver de MariaDB.", e);
        }
    }

    /**
     * Obtener la conexión actual.
     * 
     * @return Connection la conexión actual
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Obtener una nueva instancia de conexión.
     * 
     * @return Connection una nueva instancia de conexión
     * @throws SQLException si ocurre un error al conectar con la base de datos
     */
    public static Connection getConnectionInstance() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        return dbConnection.getConnection();
    }

    /**
     * Cierra la conexión.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Método principal para pruebas.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        try {
            // Crear una instancia de la conexión
            DatabaseConnection dbConnection = new DatabaseConnection();
            // Usar la conexión (en este caso, solo probarla)
            dbConnection.getConnection();

            // Cierra la conexión
            dbConnection.closeConnection();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
