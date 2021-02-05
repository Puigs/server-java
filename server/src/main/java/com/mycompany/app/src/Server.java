package com.mycompany.app.src;

import java.io.*;
import java.net.*;

public class Server {
    
    private static final Server instance = new Server();
    private int my_port;
    private Socket socket;

    private Server() {
        System.out.println("Server have been build");
    }
    
    public static final Server getInstance() {
        return instance;
    }
    
    public void init(int port) {
        this.my_port = port;
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
            System.out.println("Server is listening on port " + port);
            
            while (true) {
                this.socket = serverSocket.accept();
                
                if (this.socket.isConnected()) {
                    System.out.println("New client connected");
                    return;
                }
                
                
                /*InputStream is = this.socket.getInputStream();
                OutputStream os = this.socket.getOutputStream();
                int read;
                byte[] bytes = new byte[1024];
                while ((read = is.read(bytes)) != -1) { // blocking
                    os.write(bytes, 0, read); // blocking
                    str += bytes;
                }*/
                /*OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("Vous êtes connecté");*/
                
                /*InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String time = reader.readLine();
                System.out.println(time);
                reader.reset();
                */
            }
            
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public String do_read() {
        String str = "";
        
        try {
            InputStream input = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            str = reader.readLine().toString();
            OutputStream output = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Votre message (" + str + ") a bien été reçu");
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        return str;
    }

    public int get_port() {
        return (this.my_port);
    }
}