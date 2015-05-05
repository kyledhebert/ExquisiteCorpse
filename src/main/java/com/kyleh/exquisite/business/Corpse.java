package com.kyleh.exquisite.business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kylehebert on 4/28/15.
 * Objects of this class will represent an exquisite corpse
 * a user creates from lyric snippets.
 */
public class Corpse implements Serializable {

    private ArrayList<CorpseLyric> corpseLyrics;

    public Corpse() {
        corpseLyrics = new ArrayList<CorpseLyric>();
    }

    public Corpse(ArrayList<CorpseLyric> corpseLyrics) {
        this.corpseLyrics = corpseLyrics;
    }

    public void addLyricSnippet(CorpseLyric corpseLyric) {
        corpseLyrics.add(corpseLyric);
    }

    public void removeLyricSnippet(CorpseLyric corpseLyric) {
        corpseLyrics.remove(corpseLyric);
    }

    public ArrayList<CorpseLyric> getCorpseLyrics() {
        return corpseLyrics;
    }
}
