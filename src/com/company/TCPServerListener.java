package com.company;

import java.io.*;

public class TCPServerListener implements Runnable {

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

    public TCPServerListener(BufferedReader read)
    {
        reader = read;
    }

    private void ListenForMessages() throws IOException
    {
        String packet;
        while (true)
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
