
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Protocolos de Transporte Grado en Ingeniería Telemática Dpto. Ingeníería de
 * Telecomunicación Univerisdad de Jaén
 *
 *******************************************************
 * Práctica 4. Fichero: HttpConnection.java Versión: 1.0 Fecha: 10/2020
 * Descripción: Clase sencilla de atención al protocolo HTTP/1.1 Autor: Juan
 * Carlos Cuevas Martínez
 *
 ******************************************************
 * Alumno 1: Alumno 2:
 *
 ******************************************************/
public class HttpConnection implements Runnable {

	Socket socket = null;

	public HttpConnection(Socket s) {
		socket = s;
	}

	@Override
	public void run() {
		DataOutputStream dos = null;
		BufferedReader bis = null;
		try {
			System.out.println("Starting new HTTP connection with " + socket.getInetAddress().toString());
			dos = new DataOutputStream(socket.getOutputStream());
			
			bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line = bis.readLine();
			System.out.println("PETICIÓN["+line.length()+"]: "+line);
			//Busco el método y el recurso
			dos.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
			dos.flush();
			
			
			
			while (!(line=bis.readLine()).equals("") && line!=null) {
				System.out.println("Leído["+line.length()+"]: "+line);
				dos.write(("ECO " + line + "\r\n").getBytes());
				dos.flush();
			}
		} catch (IOException ex) {
			Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				bis.close();
				dos.close();
				socket.close();
			} catch (IOException ex) {
				Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

}
