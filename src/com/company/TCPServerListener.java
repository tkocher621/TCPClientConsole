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
        while (true) // replace
        {
            System.out.println(reader.readLine());
        }
    }

}
