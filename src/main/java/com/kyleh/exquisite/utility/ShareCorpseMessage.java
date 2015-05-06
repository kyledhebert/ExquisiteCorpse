package com.kyleh.exquisite.utility;

/**
 * Created by kylehebert on 5/4/15.
 * Objects of this class represent the message used to
 * share a Corpse object via Twitter
 */
public class ShareCorpseMessage {

    String message;
    String sharedCorpseID;


    public ShareCorpseMessage(String sharedCorpseID) {
        this.sharedCorpseID = sharedCorpseID;
        this.message = createMessage(sharedCorpseID);
    }

    private String createMessage(String sharedCorpseID) {
        message = "Check out my Exquisite Lyrics Corpse! http://localhost:8080/share.sjp?id=" +sharedCorpseID + " #exquisitelyrics";
        return message;
    }

    public String getMessage() {
        return message;
    }
}
