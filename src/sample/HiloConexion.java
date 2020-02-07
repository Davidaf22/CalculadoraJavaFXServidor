package sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloConexion extends Thread {

    public int puerto;
    public String nombre;
    public ServerSocket serverSocket;

    public HiloConexion (String nombre, int puerto) {
        super(nombre);
        this.puerto = puerto ;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run(){

        System.out.println("Creando socket servidor");
        serverSocket = null;
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Realizando el bind");
        InetSocketAddress addr = new InetSocketAddress("10.0.9.133",puerto);
        try {
            serverSocket.bind(addr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Aceptando conexiones");

        while(serverSocket!=null){

            Socket newSocket= null;
            try {
                newSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Conexion recibida");
            HiloMain hilo = new HiloMain ("secundarioThread",newSocket);
            hilo.start();

            System.out.println("Terminado");
        }

    }

}
