package com.kyleh.exquisite.business;
import com.kyleh.exquisite.utility.CorpseID;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

/**
 * Created by kylehebert on 5/4/15.
 * Objects of this class will represent an exquisite corpse that
 * gets added to the datastore for sharing with others
 */

@Entity
public class SharedCorpse extends Corpse {

    @Id String corpseID;

    private SharedCorpse() {}

    public SharedCorpse(ArrayList<CorpseLyric> corpseLyrics, String corpseID) {
        super(corpseLyrics);
        this.corpseID = corpseID;
    }

    public  String getCorpseID() {
        return corpseID;
    }
}
