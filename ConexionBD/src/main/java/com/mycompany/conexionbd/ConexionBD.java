package com.mycompany.conexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexionBD {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "Radu";
    static final String PASS = "1234";
    static final String QUERY = "SELECT * FROM videojuegos";
    /*En cada metodo tendremos que crear la conexion y cerrarla para que no de errores la base de datos.*/
    public static boolean buscaNombre(String nombre) {
        boolean salida = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(QUERY);) {

            while (rs.next()) {
                String nombreTabla = rs.getString("Nombre");
                if (nombreTabla.equals(nombre)) {
                    salida = true;
                    break;
                } else {
                    salida = false;
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
        /*Busca por nombre en la base de datos*/
    }

    public static void lanzaConsulta(String miQuery) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(QUERY);) {

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("Id") + " ");
                System.out.print("Nombre: " + rs.getString("Nombre") + " ");
                System.out.print("Genero: " + rs.getString("Genero") + " ");
                System.out.print("FechaLanzamiento: " + rs.getDate("FechaLanzamiento") + " ");
                System.out.print("Compañia: " + rs.getString("Compañia") + " ");
                System.out.println("Precio: " + rs.getFloat("Precio") + " ");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /*Me enseña los datos que tengo en la base de datos*/
    }

    public static void nuevoRegistro(String nombre, String genero, String compañia, String precio, String fecha) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            String queryInsert = "INSERT INTO `videojuegos` (`id`, `nombre`, `genero`, `fechalanzamiento`, `compañia`, `precio`) VALUES (NULL, '" + nombre + "', '" + genero + "', '" + fecha + "', '" + compañia + "', '" + precio + "')";
            stmt.executeUpdate(queryInsert);

            try (Statement stmtSelect = conn.createStatement(); ResultSet rs = stmtSelect.executeQuery(QUERY)) {

                System.out.println("");
                System.out.println("Muestra todos los videojuegos: ");
                while (rs.next()) {
                    System.out.print("  ID: " + rs.getInt("id"));
                    System.out.print(", Nombre: " + rs.getString("Nombre"));
                    System.out.print(", Genero: " + rs.getString("Genero"));
                    System.out.print(", Precio: " + rs.getFloat("Precio"));
                    System.out.print(", Compañia: " + rs.getString("Compañia"));
                    System.out.print(", Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                    System.out.println("");
                }
            }
            System.out.println("Videojuego añadido");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Mete un nuevo juego a partir de los paramettros del metodo.*/
    }

    public static void nuevoRegistroPorTeclado(String nombre, String genero, String compañia, String precio, String fecha) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            String queryInsert = "INSERT INTO `videojuegos` (`id`, `nombre`, `genero`, `fechalanzamiento`, `compañia`, `precio`) VALUES (NULL, '" + nombre + "', '" + genero + "', '" + fecha + "', '" + compañia + "', '" + precio + "')";
            stmt.executeUpdate(queryInsert);

            try (Statement stmtSelect = conn.createStatement(); ResultSet rs = stmtSelect.executeQuery(QUERY)) {

                System.out.println("");
                System.out.println("Muestra todos los videojuegos: ");
                while (rs.next()) {
                    System.out.print("  ID: " + rs.getInt("id"));
                    System.out.print(", Nombre: " + rs.getString("Nombre"));
                    System.out.print(", Genero: " + rs.getString("Genero"));
                    System.out.print(", Precio: " + rs.getFloat("Precio"));
                    System.out.print(", Compañia: " + rs.getString("Compañia"));
                    System.out.print(", Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                    System.out.println("");
                }
            }
            System.out.println("Videojuego añadido");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*Mete un nuevo juego a partir de los paramettros que le pasa el usuario*/
    }
    
    public static boolean eliminarJuego(String nombreJuego) {
        boolean resultado = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            String query = "DELETE FROM `videojuegos` WHERE `nombre` = '" + nombreJuego + "'";
            stmt.executeUpdate(query);

            try (Statement stmtSelect = conn.createStatement(); ResultSet rs = stmtSelect.executeQuery(QUERY)) {

                System.out.println("");
                System.out.println("Muestro todos los videojuegos : ");
                while (rs.next()) {
                    System.out.print("  ID: " + rs.getInt("id"));
                    System.out.print(", Nombre: " + rs.getString("Nombre"));
                    System.out.print(", Genero: " + rs.getString("Genero"));
                    System.out.print(", Precio: " + rs.getFloat("Precio"));
                    System.out.print(", Compañia: " + rs.getString("Compañia"));
                    System.out.print(", Fecha Lanzamiento: " + rs.getDate("FechaLanzamiento"));
                    System.out.println("");
                }
            }
            if (resultado == true) {
                System.out.println("Juego encontrado y eliminado!!");
            } else {
                System.out.println("Juego no se encontro y no se pudo eliminar");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
        /*Elimina el libro que tenga el nombre que le paso por el parametro del metodo.*/
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(QUERY);) {
            //METODO PARA BUSCAR NOMBRE DE JUEGO
            System.out.println("Dame un nombre que quieras buscar en la base de datos.");
            boolean respuesta = buscaNombre("Valorant");
            if (respuesta == true) {
                System.out.println("El Juego se encontro");
            } else {
                System.out.println("El juego no se encontro");
            }
            System.out.println("");
            //METODO PARA LANZAR UNA CONSULTA
            lanzaConsulta("");
            // METODO PARA AÑADIR UN NUEVO REGISTRO
            //nuevoRegistro("NuevoJuego", "Aventura", "NuevaCompañia", "49.99", "2023-01-01");
            // METODO PARA AÑADIR UN NUEVO REGISTRO POR TECLADO
                Scanner teclado = new Scanner(System.in);
                teclado.nextLine();
                String nombre, genero, fecha, compañia, precio;
                System.out.println("Introduce el nombre del videojuego : ");
                nombre = teclado.nextLine();
                System.out.println("Introduce el genero del videojuego: ");
                genero = teclado.nextLine();
                System.out.println("Introduce el nombre de la compañia del videojuego: ");
                compañia = teclado.nextLine();
                System.out.println("Introduce el precio del videojuego ");
                precio = teclado.nextLine();
                System.out.println("Introduce la fecha del videojuego (yyyy-mm-d)");
                fecha = teclado.nextLine();
            //nuevoRegistroPorTeclado(nombre, genero, compañia, precio, fecha);
            // METODO PARA ELIMINAR UN JUEGO
            eliminarJuego("a");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
