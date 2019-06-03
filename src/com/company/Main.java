package com.company;

import java.util.*;
import java.io.*;

public class Main {

    private static boolean TryParseInt(String num)
    {
        try
        {
            Integer.parseInt(num);
            return true;
        } catch (Exception x)
        {
            return false;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter server IP");
        String serverIP = scan.nextLine();
        if (serverIP.equals(""))
        {
            System.out.println("No IP selected, using default localhost.");
            serverIP = "127.0.0.1";
        }
        String port;
        do {
            System.out.println("Enter server port");
            port = scan.nextLine();
            if (port.equals(""))
            {
                System.out.println("No port selected, using default 6666.");
                port = "6666";
                break;
            }
        } while (!TryParseInt(port));

        TCPClient client = new TCPClient(serverIP, Integer.parseInt(port));
        client.Connect();

        if (!client.isConnected) return;
        System.out.println("Connected to server.");
        System.out.println("Enter your username.");
        String name = scan.nextLine();
        System.out.println("------------------------");
        System.out.println("You have entered the chat room!");
        System.out.println("------------------------");

        client.WriteMessage(name);

        String packet;
        boolean dc = false;
        do {
            packet = scan.nextLine();
            if (packet.equals("/dc"))
            {
                dc = true;
            }
            else
            {
                client.WriteMessage(packet);
            }
        }
        while (!dc); // change this to handle a client disconnecting automatically

        System.out.println("Disconnected from server.");

    }
}
