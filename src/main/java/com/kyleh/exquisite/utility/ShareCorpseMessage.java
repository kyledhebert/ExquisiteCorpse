package com.kyleh.exquisite.utility;

/**
 * Created by kylehebert on 5/4/15.
 * Objects of this class represent the message used to
 * share a Corpse object via Twitter
 */
public class ShareCorpseMessage {

    java.lang.String message;
    long corpseID;


    public ShareCorpseMessage(long corpseID) {
        this.corpseID = corpseID;
        this.message = createMessage(corpseID);
    }

    private java.lang.String createMessage(long corpseID) {
        message = "Check out my Exquisite Lyrics Corpse! http://localhost:8080/share?id=" + corpseID + " #exquisitelyrics";
        return message;
    }

    public java.lang.String getMessage() {
        return message;
    }
}
