package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {

    @FXML
    private Button conectar;
    @FXML
    private Button salir;
    @FXML
    private TextField puerto;

    private HiloMain hilo;
    private ServerSocket serverSocket;


    @FXML
    void conexion(ActionEvent event) throws IOException {

       String puertoS = puerto.getText();
        System.out.println(puertoS);
        int puertoI = Integer.parseInt(puertoS);

        System.out.println("Creando socket servidor");
        serverSocket = new ServerSocket();
        System.out.println("Realizando el bind");
        InetSocketAddress addr = new InetSocketAddress("192.168.0.27",puertoI);
        serverSocket.bind(addr);
        System.out.println("Aceptando conexiones");

        while(serverSocket != null){

            Socket newSocket= serverSocket.accept();
            System.out.println("Conexion recibida");
            hilo = new HiloMain ("secundarioThread",newSocket);
            hilo.start();

            System.out.println("Terminado");
        }
    }

    @FXML
    void desconexion (ActionEvent event) throws IOException {
        serverSocket.close();
    }
}
