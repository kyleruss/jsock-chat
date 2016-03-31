package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AppResources 
{
    private static AppResources instance;
    private BufferedImage addImage, addFriendImage, authSuccessMessage, dcImage;
    private BufferedImage broadcastImage, pmImage, updateImage, serverOkImage, serverBadImage;
    private BufferedImage msgServerImage, updateServerImage, msgSendServerImage;

    private AppResources()
    {
        initResources();
    }
    
    private void initResources()
    {
        try
        {
            addImage            =   getImageResource("add.png");
            addFriendImage      =   getImageResource("add_friend.png");
            authSuccessMessage  =   getImageResource("authsuccess.png");
            dcImage             =   getImageResource("disconnect.png");
            broadcastImage      =   getImageResource("megaphone.png");
            pmImage             =   getImageResource("private-messaging.png");
            updateImage         =   getImageResource("update.png");
            serverOkImage       =   getImageResource("server_good.png");
            serverBadImage      =   getImageResource("server_bad.png");
            msgServerImage      =   getImageResource("msg_server.png");
            updateServerImage   =   getImageResource("update_server.png");
            msgSendServerImage  =   getImageResource("message_send_server.png");
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "[Error] Failed to load resources");
        }
    }
    
    public BufferedImage getImageResource(String name) throws IOException
    {
        return ImageIO.read(new File(ServerConfig.IMAGES_DIR + name));
    }
    
    public BufferedImage getAddImage() 
    {
        return addImage;
    }

    public BufferedImage getAddFriendImage() 
    {
        return addFriendImage;
    }

    public BufferedImage getAuthSuccessMessage() 
    {
        return authSuccessMessage;
    }

    public BufferedImage getDcImage()
    {
        return dcImage;
    }

    public BufferedImage getBroadcastImage() 
    {
        return broadcastImage;
    }

    public BufferedImage getPmImage()
    {
        return pmImage;
    }

    public BufferedImage getUpdateImage()
    {
        return updateImage;
    }
    
    public BufferedImage getServerOkImage() 
    {
        return serverOkImage;
    }

    public BufferedImage getServerBadImage()
    {
        return serverBadImage;
    }
    
    public BufferedImage getMsgServerImage() 
    {
        return msgServerImage;
    }

    public BufferedImage getUpdateServerImage()
    {
        return updateServerImage;
    }
    
    public BufferedImage getMsgSendServerImage()
    {
        return msgSendServerImage;
    }
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
