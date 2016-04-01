package com.kyleruss.jsockchat.server.gui;

import com.kyleruss.jsockchat.commons.room.RoomTree;
import com.kyleruss.jsockchat.commons.user.IUser;
import com.kyleruss.jsockchat.commons.user.User;
import com.kyleruss.jsockchat.commons.user.UserList;
import com.kyleruss.jsockchat.server.core.RoomManager;
import com.kyleruss.jsockchat.server.core.ServerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class ServerPanel extends JPanel
{
    private static ServerPanel instance;
    private final LoggingList loggingList;
    private final ServerStatusPanel statusPanel;
    private final JScrollPane loggingScrollPane, userScrollPane, roomScrollPane;
    private final JPanel leftPanel, rightPanel;
    private final JSplitPane treeServerSplit;
    private final JPanel userPanel;
    private final RoomTree roomTree;
    private UserList userList;
    
    public ServerPanel()
    {
        setPreferredSize(new Dimension(ServerConfig.WINDOW_WIDTH, ServerConfig.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        
        loggingList         =   LoggingList.getInstance();
        statusPanel         =   ServerStatusPanel.getInstance();
        loggingScrollPane   =   new JScrollPane(loggingList);
        leftPanel           =   new JPanel(new GridLayout(2, 1));
        rightPanel          =   new JPanel(new BorderLayout());
        treeServerSplit     =   new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        userPanel           =   new JPanel(new BorderLayout());
        roomTree            =   new RoomTree("JSockchat Server");
        userList            =   new UserList();
        userScrollPane      =   new JScrollPane(userList);
        roomScrollPane      =   new JScrollPane(roomTree);
        
        roomTree.setFocusable(false);
        loggingList.setSelectionModel(new NonSelectionModel());
        userList.setSelectionModel(new NonSelectionModel());
        
        final int HOR_POLICY        =     ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        final int VERT_POLICY       =     ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        userScrollPane.setHorizontalScrollBarPolicy(HOR_POLICY);
        roomScrollPane.setHorizontalScrollBarPolicy(HOR_POLICY);
        userScrollPane.setVerticalScrollBarPolicy(VERT_POLICY);
        roomScrollPane.setVerticalScrollBarPolicy(VERT_POLICY);
        
        AppResources resources  =   AppResources.getInstance();
        roomTree.setServerIcon(new ImageIcon(resources.getServerImage()));
        roomTree.setRoomIcon(new ImageIcon(resources.getChatImage()));
        roomTree.setLockedRoomIcon(new ImageIcon(resources.getLockedChatImage()));
        roomTree.setUserIcon(new ImageIcon(resources.getUserImage()));
        userList.setUserIcon(new ImageIcon(resources.getUserImage()));
        roomTree.initRooms(new ArrayList<>(RoomManager.getInstance().getDataValues()));
        
        JPanel treeWrapper              =   new JPanel(new BorderLayout());
        JPanel leftContentWrapper       =   new JPanel(new BorderLayout());
        
        Border leftPanelStatusBorder    =   BorderFactory.createEmptyBorder(5, 5, 5, 5); 
        roomTree.setBorder(leftPanelStatusBorder);
        userList.setBorder(leftPanelStatusBorder);
        treeWrapper.setBorder(BorderFactory.createTitledBorder("Rooms"));
        userPanel.setBorder(BorderFactory.createTitledBorder("Online users"));
        leftContentWrapper.setBorder(BorderFactory.createEmptyBorder(8, 3, 3, 3));
        leftContentWrapper.setPreferredSize(new Dimension(350, 0));
        
        Color statusBackground  =   new Color(237, 237, 237);
        roomTree.setBackground(statusBackground);
        userList.setBackground(statusBackground);
        
        userPanel.add(userScrollPane);
        treeWrapper.add(roomScrollPane);
        leftPanel.add(treeWrapper);
        leftPanel.add(userPanel);
        leftContentWrapper.add(leftPanel);
        
        rightPanel.add(statusPanel, BorderLayout.NORTH);
        rightPanel.add(loggingScrollPane, BorderLayout.CENTER);
        treeServerSplit.setLeftComponent(leftContentWrapper);
        treeServerSplit.setRightComponent(rightPanel);
        
        add(treeServerSplit);
        
        /*try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        List<IUser> tempList2    =   new ArrayList<>();
        tempList2.add(new User("usernameA", "displayA"));
        tempList2.add(new User("usernameB", "displayB"));
        userList.setListData(tempList2.toArray());
    }
    
    private class NonSelectionModel extends DefaultListSelectionModel
    {
        @Override
        public void setSelectionInterval(int indexA, int indexB)
        {
            super.setSelectionInterval(-1, -1);
        }
    }
    
    public static ServerPanel getInstance()
    {
        if(instance == null) instance = new ServerPanel();
        return instance;
    }
}
