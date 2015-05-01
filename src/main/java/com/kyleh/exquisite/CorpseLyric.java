package com.kyleh.exquisite;

import java.io.Serializable;

/**
 * Created by kylehebert on 4/28/15.
 * Objects of this class will represent an individual lyric snippet the user
 * has decided to add to their corpse from the search results screen
 */
public class CorpseLyric implements Serializable {

    private String snippet;
    private String snippetIncluded;

    public CorpseLyric() {
        snippet = "";
    }

    public CorpseLyric(String snippet) {
        this.snippet = snippet;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSnippetIncluded() {
        return snippetIncluded;
    }

    public void setSnippetIncluded(String snippetIncluded) {
        this.snippetIncluded = snippetIncluded;
    }
}
