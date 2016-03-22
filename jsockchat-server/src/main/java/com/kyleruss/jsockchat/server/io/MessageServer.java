
package com.kyleruss.jsockchat.server.io;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class MessageServer extends Thread
{
    private static MessageServer instance;
    private boolean isListening;
    private ServerSocket serverSocket;
    
    private MessageServer()
    {
        isListening =   true;
        initSocket();
    }
    
    public synchronized void setListening(boolean isRunning)
    {
        this.isListening  =   isRunning;
        notify();
    }
    
    public boolean isListening()
    {
        return isListening;
    }
    
    private synchronized void getListeningLock()
    {
        try
        {
            if(!isListening)
                wait();
        }
        
        catch(InterruptedException e)
        {
            System.out.println("[MessageServer@getListeningLock]: " + e.getMessage());
        }
    }
    
    private void initSocket()
    {
        try
        {
            if(serverSocket != null) return;
            
            serverSocket    =   new ServerSocket(ServerConfig.MESSAGE_SERVER_PORT);
            serverSocket.setSoTimeout(ServerConfig.MESSAGE_SERVER_TIMEOUT);
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageServer@startServer]: " + e.getMessage());
        }
    }
    
    public boolean stopServer()
    {
        if(serverSocket == null || serverSocket.isClosed())
            return true;
        
        try
        {
            serverSocket.close();
            return true;
        }
        
        catch(IOException e)
        {
            System.out.println("[MessageServer@stopServer]: " + e.getMessage());
            return false;
        }
    }
    
    private void handleClientSocket(Socket socket)
    {
        MessageSocketHandler messageHandler =   new MessageSocketHandler(socket);
        messageHandler.start();
    }
    
    @Override
    public void run()
    {
        while(serverSocket != null && !serverSocket.isClosed())
        {
            getListeningLock();
            
            try
            {
                Socket clientSocket =   serverSocket.accept();
                handleClientSocket(clientSocket);
            }
            
            catch(IOException e)
            {
                System.out.println("[MessageServer@run]: " + e.getMessage());
            }
        }
    }
    
    public static MessageServer getInstance()
    {
        if(instance == null) instance = new MessageServer();
        return instance;
    }
}
