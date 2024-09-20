import java.sql.*;

public class Biblioteca {
    private Connection conexion;

    public Biblioteca() {
        conectarBD();
    }

    private void conectarBD() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            System.out.println("Conexión establecida con éxito");
            crearTablas();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private void crearTablas() {
        try {
            Statement stmt = conexion.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(100), autor VARCHAR(100), categoria VARCHAR(100))");
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(100), direccion VARCHAR(100), celular VARCHAR(100))");
            stmt.execute("CREATE TABLE IF NOT EXISTS prestamos (id INTEGER PRIMARY KEY AUTOINCREMENT, id_libro INTEGER, id_usuario INTEGER, fecha_prestamo DATE, FOREIGN KEY (id_libro) REFERENCES libros (id), FOREIGN KEY (id_usuario) REFERENCES usuarios (id))");
            System.out.println("Tablas creadas con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }

    // Método para registrar libro
    public void registrarLibro(Libro libro) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO libros (titulo, autor, categoria) VALUES (?, ?, ?)");
            ps.setString(1, libro.getTituloLibro());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.executeUpdate();
            System.out.println("Libro registrado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al registrar libro: " + e.getMessage());
        }
    }

    // Método para registrar usuario
    public void registrarUsuario(Usuario usuario) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (nombre, direccion, celular) VALUES (?, ?, ?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDireccion());
            ps.setString(3, usuario.getNumeroCelular());
            ps.executeUpdate();
            System.out.println("Usuario registrado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Método para realizar préstamo
    public void realizarPrestamo(Libro libro, Usuario usuario) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO prestamos (id_libro, id_usuario, fecha_prestamo) VALUES (?, ?, CURRENT_DATE)");
            ps.setInt(1, libro.getId());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
            System.out.println("Préstamo realizado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al realizar préstamo: " + e.getMessage());
        }
    }

    // Método para cerrar conexión
    public void cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Conexión cerrada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}


