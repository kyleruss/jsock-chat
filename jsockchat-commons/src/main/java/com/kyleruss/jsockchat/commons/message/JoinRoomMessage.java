
package com.kyleruss.jsockchat.commons.message;

public class JoinRoomMessage extends AbstractMessage
{
    private String room;
    private String attemptedPassword;

    public JoinRoomMessage(String source, String room, String password)
    {
        this.room                   =   room;
        this.attemptedPassword      =   password;
    }

    public String getRoom() 
    {
        return room;
    }

    public String getAttemptedPassword() 
    {
        return attemptedPassword;
    }
    
    public void setRoom(String room)
    {
        this.room = room;
    }

    public void setAttemptedPassword(String attemptedPassword) 
    {
        this.attemptedPassword = attemptedPassword;
    }
}
