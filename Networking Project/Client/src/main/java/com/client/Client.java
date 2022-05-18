package com.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    public static void main (String[] args) throws IOException {
        String serverAddress = "10.20.0.126"; // The server's IP address
        int PORT = 8100; // The server's port
        boolean isRunning=true;
            try (
                    Socket socket = new Socket(serverAddress, PORT);
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                while (isRunning) {
                BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
                String request=br.readLine();

                if(request.equals("exit")) {
                    out.println(request);
                    isRunning = false;
                    System.out.println("EXIT");
                }
                else if(request.equals("stop"))
                {
                    out.println(request);
                    isRunning=false;
                }
                else {
                    out.println(request);
                    String response = in.readLine();
                    System.out.println(response);
                }
                }
            } catch (UnknownHostException e) {
                System.err.println("No server listening... " + e);

        }
    }
}