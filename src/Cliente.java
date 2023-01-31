import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String ruta="";
        String contenido="";

        try {
            // 1 - Creación de socket de tipo cliente
            System.out.println("(Cliente): Creación de socket...");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion, 50000);

            // 2 - Abrir flujos de lectura y escritura
            System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();

            // 3 - Intercambio de datos con el servidor
            System.out.println("(Cliente): Envía la ruat al servidor...");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);


            ruta = leeRuta();
            bw.write(ruta);
            bw.newLine();
            bw.flush();

            System.out.println("(Cliente): Lectura del mensaje del servidor...");

            contenido = br.readLine();
            mensajeSalida(contenido);

            // 4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
            bw.close();
            osw.close();
            br.close();
            isr.close();

            is.close();
            os.close();

            // 5 - Cerrar la conexión
            socketCliente.close();

        } catch (UnknownHostException e) {
            System.err.println("ERROR: No se encuentra el host");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR: Problema con la conexión");
            e.printStackTrace();
        }
    }

    public static String leeRuta() {
        String ruta;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca la ruta del fichero:");
        ruta = sc.next();
        sc.close();

        return ruta;
    }

    public static void mensajeSalida(String contenido) {
        System.out.println(contenido);
    }
}
