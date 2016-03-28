
package com.kyleruss.jsockchat.client.message;

import com.kyleruss.jsockchat.commons.message.AuthMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class ClientAuthMessage extends ClientMessage implements AuthMessage
{
    private String username;
    private String password;
    private boolean rememberPassword;
    
    public ClientAuthMessage(String source)
    {
        super(source);
    }
    
    @Override
    public void clientAction(ResponseMessage response) {
    }

    @Override
    public void witnessAction(ResponseMessage response) {
    }

    @Override
    public String getUsername() 
    {
        return username;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public boolean rememberPassword() 
    {
        return rememberPassword;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public void setRememberPassword(boolean rememberPassword) 
    {
        this.rememberPassword = rememberPassword;
    }
}
