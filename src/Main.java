import javax.swing.plaf.nimbus.State;
import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) { Connection connection = null;
        try {
            // conecion a la base de datos
            connection = getConnection();

            // damos la orden a la base de datos
            Statement statement = connection.createStatement();

            // DROP-CREATE crear la tabla
            statement.execute("DROP TABLE IF EXISTS FIGURA; CREATE TABLE FIGURA (ID INT PRIMARY KEY, " +
                    "COLOR VARCHAR(50) NOT NULL, " +
                    "TIPO VARCHAR(50) NOT NULL)");

            // INSERTAR VALORES EN LA TABLA
            statement.execute("INSERT INTO FIGURA (ID, COLOR, TIPO) VALUES (1, 'rojo', 'circulo'), " +
                    "(2, 'azul', 'circulo') , (3, 'verde', 'cuadrado'), (4, 'rojo', 'cuadrado'), (5, 'amarillo', 'cuadrado')");

            //CONSULTAR BD
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FIGURA WHERE TIPO = 'circulo' AND COLOR = 'rojo'");

            // usamos un bucle para recorre toda la informacion, con el bucle vamos a traer registro por registro
            //lo que hace next() es que chequea si hay un siguiente registro y devuelve un booleano

            while (resultSet.next()) {
                System.out.println("Color: " + resultSet.getString(2) + "\nTipo: " +
                        resultSet.getString(3));
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        // como es muy caro conectarse a una BD (en tiempo) nos vamos a conectar y finalmente desconectarse con un finally

        finally {
            //cerra la conexion
            try {
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    // salimos del metodo main
    // hay que empezar por aca porque sino te salta arriba en el connection = getConnection(); un error
    private static Connection getConnection() throws ClassNotFoundException, SQLException /* Exception tambien funciona */ {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./mesas8","sa", "sa");
    }
    }
