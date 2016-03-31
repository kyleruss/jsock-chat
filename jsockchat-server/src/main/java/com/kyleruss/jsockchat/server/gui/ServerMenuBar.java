package com.kyleruss.jsockchat.server.gui;

import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ServerMenuBar extends JMenuBar
{
    private final JMenu aboutMenu;
    private final JMenu logsMenu;
    private final JMenu serverMenu;
    private final JMenu msgServerMenu;
    private final JMenu updateServerMenu;
    private Map<String, JMenuItem> items;
    private static ServerMenuBar instance;
    
    public ServerMenuBar()
    {
        aboutMenu           =   new JMenu("About");
        logsMenu            =   new JMenu("Logs");
        serverMenu          =   new JMenu("Server");
        msgServerMenu       =   new JMenu("Message server");
        updateServerMenu    =   new JMenu("Update broadcast server");
        
        addItem("msgSvStopItem", new JMenuItem("Stop"), msgServerMenu);
        addItem("msgSvStartItem", new JMenuItem("Start"), msgServerMenu);
        addItem("updateSvStartItem", new JMenuItem("Start"), updateServerMenu);
        addItem("updateSvStopItem", new JMenuItem("Stop"), updateServerMenu);
        addItem("authorItem", new JMenuItem("Author"), aboutMenu);
        addItem("logClearItem", new JMenuItem("Clear"), logsMenu);
    }
    
    public void addItem(String name, JMenuItem item, JMenu menu)
    {
        if(items.containsKey(name))
        {
            items.put(name, item);
            menu.add(item);
        }
    }
    
    public JMenuItem getItem(String itemName)
    {
        return items.get(itemName);
    }
    
    public void setListener(ActionListener listener)
    {
        for(JMenuItem item : items.values())
            item.addActionListener(listener);
    }
    
    public JMenu getAboutMenu()
    {
        return aboutMenu;
    }

    public JMenu getLogsMenu() 
    {
        return logsMenu;
    }

    public JMenu getServerMenu() 
    {
        return serverMenu;
    }
    
    public JMenu getMsgServerMenu()
    {
        return msgServerMenu;
    }

    public JMenu getUpdateServerMenu() 
    {
        return updateServerMenu;
    }
    
    public static ServerMenuBar getInstance()
    {
        if(instance == null) instance = new ServerMenuBar();
        return instance;
    }
}