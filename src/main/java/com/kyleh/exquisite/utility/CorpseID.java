package com.kyleh.exquisite.utility;
import org.hashids.Hashids;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kylehebert on 5/3/15.
 * Objects of this class represent a unique ID for each Corpse object
 * These IDs will be used for adding and retrieving a Corpse object from
 * the Datastore, and generating share URLs
 */
public class CorpseID {

    private long corpseID;

    public CorpseID() {
        this.corpseID = createCorpseID();
    }

    public long getCorpseID() {
        return corpseID;
    }

    /**
     * Each Shared Corpse will have a long based on timestamp,
     * this vallue will be stored as the key in the datastore
     */
    private long createCorpseID() {
        //based on http://stackoverflow.com/questions/9191288/creating-a-unique-timestamp-in-java
        AtomicLong atomicLong = new AtomicLong();
        long timestamp = System.currentTimeMillis();
        while (true) {
            long lastTime = atomicLong.get();
            if (lastTime >= timestamp) {
                timestamp = lastTime+1;
            }
            if (atomicLong.compareAndSet(lastTime,timestamp)) {
                return timestamp;
            }
        }
    }

    public String toString() {
        return String.valueOf(corpseID);
    }

    /**
     * The hashed ID will be used in the sharing URL
     * @return
     */
    public String createCorpseIDHash() {

        //generate a long based on timestamp
        long timestamp = getCorpseID();

        //using Hashids, convert long to hash
        Hashids hashids = new Hashids("that is an exquisite corpse");

        return hashids.encode(timestamp);
    }









}
