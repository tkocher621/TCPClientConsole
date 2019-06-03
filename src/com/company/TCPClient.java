package com.company;

import java.io.*;
import java.net.*;

public class TCPClient {

    public boolean isConnected;

    private String ip;
    private int port;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public TCPClient(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        isConnected = false;
    }

    public void WriteMessage(String message)
    {
        writer.println(message);
    }

    public void Connect()
    {
        try
        {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new TCPServerListener(socket, reader)).start();
            isConnected = true;
        }
        catch (Exception x)
        {
            System.out.println("Failed to connect to server, socket error: " + x.getMessage());
            isConnected = false;
        }
    }

    public void Disconnect() throws IOException
    {
        if (socket.isConnected())
        {
            socket.close();
            isConnected = false;
        }
    }

}
