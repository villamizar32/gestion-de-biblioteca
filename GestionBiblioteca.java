import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Libro {
    private String tituloLibro;
    private String autor;
    private String categoria;
    private boolean disponible;

    public Libro(String tituloLibro, String autor, String categoria) {
        this.tituloLibro = tituloLibro;
        this.autor = autor;
        this.categoria = categoria;
        this.disponible = true;
    }

    // Getters y setters
    public String getTituloLibro() {
        return tituloLibro;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "tituloLibro='" + tituloLibro + '\'' +
                ", autor='" + autor + '\'' +
                ", categoria='" + categoria + '\'' +
                ", disponible=" + disponible +
                '}';
    }

    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}

class Usuario {
    private String nombre;
    private String direccion;
    private String numeroCelular;

    public Usuario(String nombre, String direccion, String numeroCelular) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroCelular = numeroCelular;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numeroCelular='" + numeroCelular + '\'' +
                '}';
    }

    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}

class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo, Date fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters
    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "libro=" + libro +
                ", usuario=" + usuario +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}

public class GestionBiblioteca {
    private static final String MENSAJE_LIBRO_AÑADIDO = "Libro añadido con éxito";
    private static final String MENSAJE_USUARIO_AÑADIDO = "Usuario añadido con éxito";
    private static final String MENSAJE_PRESTAMO_REALIZADO = "Préstamo realizado con éxito";
    private static final String MENSAJE_DEVOLUCION_REGISTRADA = "Devolución registrada con éxito";
    private static final String MENSAJE_LIBRO_NO_DISPO = "Libro no disponible";
    private static final String MENSAJE_LIBRO_NO_ENCONTRADO = "Libro no encontrado";

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public GestionBiblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void registrarLibro(Libro libro) {
        libros.add(libro);
        System.out.println(MENSAJE_LIBRO_AÑADIDO);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println(MENSAJE_USUARIO_AÑADIDO);
    }

    public void realizarPrestamo(Libro libro, Usuario usuario, Date fechaPrestamo, Date fechaDevolucion) {
        if (libro.isDisponible()) {
            Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion);
            prestamos.add(prestamo);
            libro.setDisponible( false );
            
            System.out.println(MENSAJE_PRESTAMO_REALIZADO);
            



        } else {
            System.out.println(MENSAJE_LIBRO_NO_DISPO);
        }
    }

    public void registrarDevolucion(Libro libro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().equals(libro)) {
                libro.setDisponible(true);
                System.out.println(MENSAJE_DEVOLUCION_REGISTRADA);
                return;
            }
        }
        System.out.println(MENSAJE_LIBRO_NO_ENCONTRADO);
    }

    public void consultarDisponibilidad(Libro libro) {
        System.out.println("Disponibilidad: " + libro.isDisponible());
    }

    public static void main(String[] args) {
        GestionBiblioteca biblioteca = new GestionBiblioteca();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Consultar disponibilidad");
            System.out.println("6. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese categoría del libro: ");
                    String categoria = scanner.nextLine();
                    Libro libro = new Libro(titulo, autor, categoria);
                    biblioteca.registrarLibro(libro);
                    break;
                case 2:
                    System.out.print("Ingrese nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese dirección del usuario: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Ingrese número de celular del usuario: ");
                    String numeroCelular = scanner.nextLine();
                    Usuario usuario = new Usuario(nombre, direccion, numeroCelular);
                    biblioteca.registrarUsuario(usuario);
                    break;
                case 3:
                    System.out.print("Ingrese título del libro: ");
                    titulo = scanner.nextLine();
                    Libro libroPrestamo = null;
                    for (Libro l : biblioteca.libros) {
                        if (l.getTituloLibro().equals(titulo)) {
                            libroPrestamo = l;
                            break;
                        }
                    }
                    if (libroPrestamo != null) {
                        System.out.print("Ingrese nombre del usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        Usuario usuarioPrestamo = null;
                        for (Usuario u : biblioteca.usuarios) {
                            if (u.getNombre().equals(nombreUsuario)) {
                                usuarioPrestamo = u;
                                break;
                            }
                        }
                        if (usuarioPrestamo != null) {
                            Date fechaPrestamo = new Date();
                            Date fechaDevolucion = new Date();
                            biblioteca.realizarPrestamo(libroPrestamo, usuarioPrestamo, fechaPrestamo, fechaDevolucion);
                        } else {
                            System.out.println("Usuario no encontrado");
                        }
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese título del libro: ");
                    titulo = scanner.nextLine();
                    Libro libroDevolucion = null;
                    for (Libro l : biblioteca.libros) {
                        if (l.getTituloLibro().equals(titulo)) {
                            libroDevolucion = l;
                            break;
                        }
                    }
                    if (libroDevolucion != null) {
                        biblioteca.registrarDevolucion(libroDevolucion);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese título del libro: ");
                    titulo = scanner.nextLine();
                    Libro libroConsulta = null;
                    for (Libro l : biblioteca.libros) {
                        if (l.getTituloLibro().equals(titulo)) {
                            libroConsulta = l;
                            break;
                        }
                    }
                    if (libroConsulta != null) {
                        biblioteca.consultarDisponibilidad(libroConsulta);
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }
}