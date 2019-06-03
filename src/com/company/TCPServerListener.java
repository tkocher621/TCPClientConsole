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
            System.out.println("Error: " + x.getMessage());
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
                System.out.println(reader.readLine());
            }
            else
            {
                System.out.println("Unknown error communicating with server.\nClient has been disconnected.");
                break;
            }
        }
    }

}
