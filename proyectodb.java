import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;



public class proyectodb {
    
    public static void insertar() {
        try (Connection conn = conexion.conectar()) {
            Scanner sc = new Scanner(System.in);

            System.out.print("DNI: ");
            int dni = sc.nextInt(); sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Fecha Nacimiento(AAAA,MM,DD): ");
            String fechaStr = sc.nextLine();
            // Convertimos LocalDate a java.sql.Date para SQL
            java.sql.Date fecha_nac = java.sql.Date.valueOf(fechaStr);
            System.out.print("Facebook: ");
            String facebook = sc.nextLine();
            System.out.print("Codigo Postal: ");
            Integer cod_postal = sc.nextInt(); sc.nextLine();
            System.out.print("Direccion: ");
            String direccion = sc.nextLine();

            String sql = "INSERT INTO Donaciones.padrino(dni, nombre, apellido, email, fecha_nac, facebook, cod_postal, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dni);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, email);
            stmt.setDate(5, fecha_nac);
            stmt.setString(6, facebook);
            stmt.setInt(7, cod_postal);
            stmt.setString(8, direccion);
            stmt.executeUpdate();
            //" Padrino insertado correctamente.");
            while (true){
                System.out.println("Ingrese 1 (si es donante) o 2 (si es contacto) : ");
                int tip = sc.nextInt(); sc.nextLine();
                if (tip == 1){
                    System.out.print("Cuil: ");
                    String cuil = sc.nextLine();
                    System.out.print("Ocupacion: ");
                    String ocupacion = sc.nextLine();
                    sql = "INSERT INTO Donaciones.donante(dni, cuil, ocupacion) VALUES (?, ?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, dni);
                    stmt.setString(2, cuil);
                    stmt.setString(3, ocupacion);
                    stmt.executeUpdate();

                    System.out.println("Ingrese la cantidad de aportes que realizo: ");
                    int cant = sc.nextInt(); sc.nextLine();
                    for (int i = 1; i<= cant; i++){
                        System.out.println("Aporte " + i);
                        System.out.println("Nombre del programa: ");
                        String nombre_programa = sc.nextLine();
                        System.out.println("Monto: ");
                        Double monto = sc.nextDouble(); sc.nextLine();
                        int opcion = -1;
                        String frecuencia = "";
                        while(true) {
                            try {
                                System.out.println("\n---Ingrese la Frecuencia --");
                                System.out.println("1. Semanal");
                                System.out.println("2. Mensual");
                                System.out.print("Elige una opción: ");
                                if (!sc.hasNextInt()) {
                                    System.out.println("Debes ingresar un número.");
                                    sc.next(); // descartamos entrada incorrecta
                                    continue;
                                }

                                opcion = sc.nextInt(); 
                                sc.nextLine(); // limpiar salto de línea
                                switch (opcion) {
                                    case 1:
                                        frecuencia = "SEMANAL";
                                        break;
                                    case 2:
                                        frecuencia = "MENSUAL";
                                        break;
                                    default:
                                        System.out.println("Opción inválida.");
                                }

                            } catch (Exception e) {
                                System.out.println("Ocurrió un error: " + e.getMessage());
                                sc.nextLine(); // limpiar buffer si hubo error
                            }
                            if (opcion >= 1 && opcion <= 2)
                                break;
                        }
                        sql = "INSERT INTO Donaciones.Aporte(dni, nombre_programa, monto, frecuencia) VALUES (?, ?, ?, ?::Donaciones.tFrecuencia)";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, dni);
                        stmt.setString(2, nombre_programa);
                        stmt.setDouble(3, monto);
                        stmt.setString(4, frecuencia);
                        stmt.executeUpdate();
                    }
                    break;
                }else if (tip == 2) {
                    System.out.print("Fecha alta: ");
                    fechaStr = sc.nextLine();
                    java.sql.Date fecha_alta = java.sql.Date.valueOf(fechaStr);
                    System.out.print("Fecha baja: ");
                    fechaStr = sc.nextLine();
                    java.sql.Date fecha_baja = java.sql.Date.valueOf(fechaStr);
                    System.out.print("Fecha primer contacto: ");
                    fechaStr = sc.nextLine();
                    java.sql.Date fecha_primer_contacto = java.sql.Date.valueOf(fechaStr);
                    System.out.print("Fecha rechazo: ");
                    fechaStr = sc.nextLine();
                    java.sql.Date fecha_rechazo = java.sql.Date.valueOf(fechaStr);
                    String estado = "";
                    int opcion = -1;
                    while(true) {
                        try {
                            System.out.println("\n--- Estados --");
                            System.out.println("1. Sin llamar");
                            System.out.println("2. ERROR");
                            System.out.println("3. En gestion");
                            System.out.println("4. Adherido");
                            System.out.println("5. Amigo");
                            System.out.println("6. No acepta");
                            System.out.println("7. Baja");
                            System.out.println("8. Voluntario");
                            System.out.print("Elige una opción: ");
                            if (!sc.hasNextInt()) {
                                System.out.println("Debes ingresar un número.");
                                sc.next(); // descartamos entrada incorrecta
                                continue;
                            }

                            opcion = sc.nextInt(); 
                            sc.nextLine(); // limpiar salto de línea

                            switch (opcion) {
                                case 1:
                                    estado = "Sin llamar";
                                    break;
                                case 2:
                                    estado = "ERROR";
                                    break;
                                case 3:
                                    estado  = "En gestion";
                                    break;
                                case 4:
                                    estado  = "Adherido";
                                    break;
                                case 5:
                                    estado = "Amigo";
                                    break;
                                case 6:
                                    estado = "No acepta";
                                    break;
                                case 7:
                                    estado  = "Baja";
                                    break;
                                case 8:
                                    estado  = "Voluntario";
                                    break;
                                default:
                                    System.out.println("Opción inválida.");
                            }

                        } catch (Exception e) {
                            System.out.println("Ocurrió un error: " + e.getMessage());
                            sc.nextLine(); // limpiar buffer si hubo error
                        }
                        if (opcion >= 1 && opcion <= 8)
                            break;
                    }
                    sql = "INSERT INTO Donaciones.contacto(dni, estado, fecha_primer_contacto, fecha_alta, fecha_baja, fecha_rechazo) VALUES (?, ?, ?, ?, ?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, dni);
                    stmt.setString(2, estado);
                    stmt.setDate(3, fecha_primer_contacto);
                    stmt.setDate(4, fecha_alta);
                    stmt.setDate(5, fecha_baja);
                    stmt.setDate(6, fecha_rechazo);
                    stmt.executeUpdate();
                    break; 
                }else{
                    System.out.println("Entrada invalida ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void eliminar() {
        try (Connection conn = conexion.conectar()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("DNI del donante a eliminar: ");
            int dni = sc.nextInt();

            String sql = "DELETE FROM Donaciones.Donante WHERE Dni = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dni);
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                System.out.println(" Donante eliminado correctamente.");
            } else {
                System.out.println(" Donante no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listar() {
        try (Connection conn = conexion.conectar()) {
            String sql = 
                "SELECT p.Dni, p.apellido, p.nombre, a.nombre_programa, a.monto, a.frecuencia " + 
                "FROM Donaciones.Padrino p " + 
                "JOIN Donaciones.Donante d ON p.Dni = d.Dni " + 
                "JOIN Donaciones.Aporte a ON d.Dni = a.Dni "
            ;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println(" Listado de padrinos con aportes:");
            while (rs.next()) {
                System.out.printf("DNI: %d - %s %s - Programa: %s - $%.2f - %s\n",
                        rs.getInt("Dni"),
                        rs.getString("apellido"),
                        rs.getString("nombre"),
                        rs.getString("nombre_programa"),
                        rs.getDouble("monto"),
                        rs.getString("frecuencia"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            try {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Insertar padrino");
                System.out.println("2. Eliminar donante");
                System.out.println("3. Listar padrinos con programas");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Debes ingresar un número.");
                    sc.next(); // descartamos entrada incorrecta
                    continue;
                }

                opcion = sc.nextInt();
                sc.nextLine(); // limpiar salto de línea

                switch (opcion) {
                    case 1:
                        insertar();
                        break;
                    case 2:
                        eliminar();
                        break;
                    case 3:
                        listar();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                sc.nextLine(); // limpiar buffer si hubo error
            }
        } while (opcion != 0);

        sc.close();
    }
}


