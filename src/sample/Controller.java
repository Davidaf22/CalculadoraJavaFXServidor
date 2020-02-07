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

  public HiloConexion hilo;


    @FXML
    void conexion(ActionEvent event) throws IOException {

        String puertoS = puerto.getText();
        int puertoI = Integer.parseInt(puertoS);

        hilo = new HiloConexion ("Thread Conexion",puertoI);
        hilo.start();
    }

    @FXML
    void desconexion (ActionEvent event) throws IOException {

        System.out.println("Cerrando socket");
        hilo.setServerSocket(null);
        hilo.getServerSocket().close();


    }
}
