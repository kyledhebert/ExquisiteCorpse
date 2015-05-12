package com.kyleh.exquisite.business;

import java.io.Serializable;

/**
 * Created by kylehebert on 4/28/15.
 * Objects of this class will represent an individual lyric snippet the user
 * has decided to add to their corpse from the search results screen
 */

public class CorpseLyric implements Serializable {

    private String snippet;
    private String snippetID;
    private String snippetIncluded;

    public CorpseLyric() {
        snippet = "";
        snippetID = "";
    }

    public CorpseLyric(String snippetID, String snippet) {
        this.snippetID = snippetID;
        this.snippet = snippet;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }


    public String getSnippetID() {
        return snippetID;
    }

    public void setSnippetID(String snippetID) {
        this.snippetID = snippetID;
    }
}
