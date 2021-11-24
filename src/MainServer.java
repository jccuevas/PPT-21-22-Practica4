
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Protocolos de Transporte
 * Grado en Ingeniería Telemática
 * Dpto. Ingeníería de Telecomunicación
 * Univerisdad de Jaén
 *
 *******************************************************
 * Práctica 4.
 * Fichero: MainServer.java
 * Versión: 1.0
 * Fecha: 10/2020
 * Descripción:
 * 	Servidor sencillo multi-hebra Socket TCP para atención al protocolo HTTP/1.1
 * Autor: Juan Carlos Cuevas Martínez
 *
 ******************************************************
 * Alumno 1:
 * Alumno 2:
 *
 ******************************************************/
public class MainServer {
    
    static ServerSocket server=null;
    static final short TCP_PORT = 80;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            InetAddress serveraddr = InetAddress.getLocalHost();
            server = new ServerSocket(TCP_PORT,5,serveraddr);
            System.out.println("Simple HTTP/1.1. Server waiting in "+serveraddr+" port "+TCP_PORT);
            while(true){
                Socket s = server.accept();
                HttpConnection conn = new HttpConnection(s);
                new Thread(conn).start();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
}
