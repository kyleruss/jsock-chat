package com.kyleruss.jsockchat.client.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.swingx.prompt.PromptSupport;


public class ConnectPanel extends LoginPanel
{
    private JTextField hostField, portField;
    
    public ConnectPanel()
    {
        submitButton.setIcon(new ImageIcon(AppResources.getInstance().getConnectImage()));
        titleLabel.setText("Connect");
        titleLabel.setIcon(new ImageIcon(AppResources.getInstance().getServerImage()));
        fieldPanel.removeAll();
        
        hostField   =   new JTextField();
        portField   =   new JTextField();
        
        PromptSupport.setPrompt(" Server hostname/address", hostField);
        PromptSupport.setPrompt(" Port number of server", portField);
        
        hostField.setPreferredSize(usernameField.getPreferredSize());
        portField.setPreferredSize(usernameField.getPreferredSize());
        hostField.setBorder(fieldBorder);
        portField.setBorder(fieldBorder);
        
        fieldPanel.add(titleLabel, "wrap");
        fieldPanel.add(new JLabel("Address"));
        fieldPanel.add(hostField, "wrap");
        fieldPanel.add(new JLabel("Port"));
        fieldPanel.add(portField, "wrap");
        fieldPanel.add(processingPanel, "span 2, al center, gapy 15");
    }
    
    @Override
    protected void submit()
    {
        showProcessing(true);
    }
}
