package com.kyleruss.jsockchat.server.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class LoggingList extends JList
{
    private static LoggingList instance;
    private final DefaultListModel model;
    
    private LoggingList()
    {
        setBackground(Color.BLACK);
        model   =   new DefaultListModel();
        setModel(model);
        setSelectionModel(new NonSelectionModel());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new MessageCellRenderer());
    }
    
    public void logMessage(LogMessage message)
    {
        model.addElement(message);
    }
    
    public static LoggingList getInstance()
    {
        if(instance == null) instance = new LoggingList();
        return instance;
    }
    
    private class MessageCellRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel label        =   (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            LogMessage message  =   (LogMessage) value;   
            label.setIcon(new ImageIcon(message.getMessageImage()));
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            
            return label;
        }
    }
    
    private class NonSelectionModel extends DefaultListSelectionModel
    {
        @Override
        public void setSelectionInterval(int indexA, int indexB)
        {
            super.setSelectionInterval(-1, -1);
        }
    }
}