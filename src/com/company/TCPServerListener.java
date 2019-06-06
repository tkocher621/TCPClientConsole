package com.company;

import java.io.*;
import java.net.*;

public class TCPServerListener implements Runnable {

    private Socket client;
    private BufferedReader reader;

    public void run()
    {
        try
        {
            ListenForMessages();
        }
        catch (IOException x)
        {
            if (!client.isConnected())
            {
                System.out.println("Error: " + x.getMessage());
            }
        }
    }

    public TCPServerListener(Socket clnt, BufferedReader read)
    {
        client = clnt;
        reader = read;
    }

    private void ListenForMessages() throws IOException
    {
        String packet;
        while (client.isConnected())
        {
            packet = reader.readLine();
            if (packet != null)
            {
                System.out.println(packet);
            }
            else
            {
                if (!client.isConnected())
                {
                    System.out.println("Unknown error communicating with server.");
                }
                System.out.println("Client has been disconnected.");
                client.getInputStream().close();
                break;
            }
        }
    }

}
