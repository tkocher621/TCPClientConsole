package com.company;

import java.io.*;
import java.net.*;

public class TCPClient {

    public boolean isConnected;

    private String ip;
    private int port;
    private PrintWriter writer;
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

    public void Connect() throws IOException
    {
        try
        {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            new Thread(new TCPServerListener(socket, new BufferedReader(new InputStreamReader(socket.getInputStream())))).start();
            isConnected = true;
        }
        catch (Exception x)
        {
            System.out.println("Failed to connect to server, socket error: " + x.getMessage());
            isConnected = false;
            socket.getInputStream().close();
        }
    }

    public void Disconnect() throws IOException
    {
        if (socket.isConnected())
        {
            socket.getInputStream().close();
            isConnected = false;
        }
    }

}
