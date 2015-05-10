package com.kyleh.exquisite.business;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by kylehebert on 5/4/15.
 * Objects of this class will represent an exquisite corpse that
 * gets added to the datastore for sharing with others
 */

@Entity
public class SharedCorpse extends Corpse {

    @Id long corpseID;

    public SharedCorpse(){

    }


    public SharedCorpse(ArrayList<CorpseLyric> corpseLyrics, long corpseID) {
        super(corpseLyrics);
        this.corpseID = corpseID;
    }

}
