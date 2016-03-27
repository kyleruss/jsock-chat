
package com.kyleruss.jsockchat.client.core;

import com.kyleruss.jsockchat.client.config.ClientConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;


public class SocketManager 
{
    private static SocketManager instance;
    private Socket tcpSocket;
    private DatagramSocket udpSocket;

    private SocketManager()
    {
        initSocket();
    }
    
    private void initSocket()
    {
        try
        {
            tcpSocket   =   new Socket(ClientConfig.MSG_SERVER_HOST, ClientConfig.MSG_SERVER_PORT);
            udpSocket   =   new DatagramSocket(ClientConfig.UPDATE_LIST_PORT);
        }
        
        catch(IOException e)
        {
            System.out.println("[SocketManager@initSocket]: " + e.getMessage());
        }
    }
    
    public Socket getTcpSocket()
    {
        return tcpSocket;
    }
    
    public DatagramSocket getUdpSocket()
    {
        return udpSocket;
    }
    
    public static SocketManager getInstance()
    {
        if(instance == null) instance = new SocketManager();
        return instance;
    }
}
