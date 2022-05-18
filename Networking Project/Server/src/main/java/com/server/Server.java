package com.server;

import com.model.Person;
import com.model.SocialNetwork;
import com.svg.SvgExport;

import javax.lang.model.element.PackageElement;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static final int PORT = 8100;
    static ServerSocket serverSocket = null;

    Server() throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                if(!serverSocket.isClosed()) {
                    System.out.println("Waiting for a client...");
                    Socket socket = serverSocket.accept();
                    ClientThread clientThread = new ClientThread(socket);
                    clientThread.start();
                }
                else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            List<Person> socialNetwork= SocialNetwork.getSocialNetwork();
            SvgExport svgExport= new SvgExport(socialNetwork);
            svgExport.export();
            serverSocket.close();
        }
    }
    public static void stopServer() throws IOException {
        serverSocket.close();
    }


    public static void main(String[] args) throws IOException {
        Server server= new Server();

    }
}
