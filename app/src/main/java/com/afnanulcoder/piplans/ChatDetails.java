package com.afnanulcoder.piplans;

public class ChatDetails
{
    String senderID, senderName, recieverName, recieverID, theMessage;

    public ChatDetails() {
    }

    public ChatDetails(String senderID, String senderName, String recieverID, String recieverName, String theMessage) {
        this.senderID = senderID;
        this.senderName = senderName;
        this.recieverID = recieverID;
        this.recieverName = recieverName;
        this.theMessage = theMessage;
    }



    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(String recieverID) {
        this.recieverID = recieverID;
    }

    public String getTheMessage() {
        return theMessage;
    }

    public void setTheMessage(String theMessage) {
        this.theMessage = theMessage;
    }

}
