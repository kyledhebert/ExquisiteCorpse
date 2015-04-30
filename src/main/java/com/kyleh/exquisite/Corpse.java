package com.kyleh.exquisite;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kylehebert on 4/28/15.
 * Objects of this class will represent an exquisite corpse
 * a user creates from lyric snippets.
 */
public class Corpse implements Serializable {

    ArrayList<CorpseLyric> corpseLyricArrayList;

    public Corpse() {
        corpseLyricArrayList = new ArrayList<CorpseLyric>();
    }

    public Corpse(ArrayList<CorpseLyric> corpseLyricArrayList) {
        this.corpseLyricArrayList = corpseLyricArrayList;
    }

    public void addLyricSnippet(CorpseLyric corpseLyric) {
        corpseLyricArrayList.add(corpseLyric);
    }

    public void displayCorpse() {
        for (CorpseLyric corpseLyric: corpseLyricArrayList) {
            System.out.println(corpseLyric + "\n");
        }
    }



}
