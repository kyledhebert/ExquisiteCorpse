package com.kyleh.exquisite.controllers;

import com.googlecode.objectify.Result;
import com.kyleh.exquisite.business.Corpse;
import com.kyleh.exquisite.business.CorpseLyric;
import com.kyleh.exquisite.business.SearchResult;
import com.kyleh.exquisite.business.SharedCorpse;
import com.kyleh.exquisite.utility.CorpseID;

import com.kyleh.exquisite.utility.ShareCorpseMessage;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.jmusixmatch.snippet.Snippet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import com.googlecode.objectify.ObjectifyService;



/**
 * Created by kylehebert on 4/24/15.
 * Servlet responsible for searching for and adding lyrics
 * to a Corpse object.
 */
public class CorpseController extends HttpServlet {

    TrackData trackData;
    Track track;
    Lyrics lyrics;
    Snippet snippet;

    MusixMatch musixMatch = new MusixMatch(getMusixMatchAPIKey());

    //register classes for Objectify
    static {
        ObjectifyService.register(SharedCorpse.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        java.lang.String url = "/index.jsp";
        ServletContext servletContext = getServletContext();

        //get current action
        java.lang.String action = request.getParameter("action");
        if (action == null) {
            action = "search"; //default action
        }

        //perform action and set URL to appropriate page
        if (action.equals("search")) {
            url = "/index.jsp"; //the search page
        }

        //search for and display the lyric snippet at result.jsp
        else if (action.equals("result")) {


            java.lang.String artistSearch = request.getParameter("artist");
            java.lang.String trackSearch = request.getParameter("track");


            //Fuzzy Search
            try {
                track = musixMatch.getMatchingTrack(trackSearch, artistSearch);

            } catch (MusixMatchException e) {
                System.out.println("Artist or track not found");
                e.printStackTrace();

            }

            trackData = track.getTrack();

            java.lang.String artist = trackData.getArtistName();
            java.lang.String track = trackData.getTrackName();

            //validate the search parameters
//            String message;
//            if (artist == null || track == null || artist.isEmpty() || track.isEmpty()) {
//                message = "Please enter text into both boxes.";
//                url = "/index.jsp";
//            }



            //snippet search
            int trackID = trackData.getTrackId();
            java.lang.String resultID = Integer.toString(trackID);

            try {
                snippet = musixMatch.getSnippet(trackID);
            } catch (MusixMatchException e) {
                e.printStackTrace();
            }
            java.lang.String lyricSnippet = snippet.getSnippetBody();

            //find the first newline character in the result so we can create a snippet
            //int newline = lyricsBody.indexOf("\n");
            //String lyricSnippet = lyricsBody.substring(0,newline);

            //store results in SearchResult object and create a session
            HttpSession session = request.getSession();
            SearchResult searchResult = new SearchResult(resultID,artist,track,lyricSnippet);

            session.setAttribute("result", searchResult);


            //set SearchResult Object in request object and set URL
            request.setAttribute("searchResult", searchResult);
            url = "/result.jsp"; //the results page

        }

        //convert result to CorpseLyric object and add to Corpse object
        else if (action.equals("add")) {

            //String snippet = searchResult.getSnippet();

            //create a a new corpse and new corpse session if needed
            HttpSession session = request.getSession();
            Corpse corpse = (Corpse) session.getAttribute("corpse");
            if (corpse == null) {
                request.setAttribute("emptyCorpse", "You Corpse has no lyrics");
                corpse = new Corpse();
            }

            SearchResult searchResult = (SearchResult) session.getAttribute("result");

            CorpseLyric corpseLyric = new CorpseLyric(searchResult.getResultID(),searchResult.getSnippet());

            //set the lyric's session ID to the snippet ID
            //this will be useful for removing lyrics later
            session.setAttribute(corpseLyric.getSnippetID(),corpseLyric);


            corpse.addLyricSnippet(corpseLyric);

            session.setAttribute("corpse", corpse);
            url = "/corpse.jsp";

        }

        else if (action.equals("remove")) {
            HttpSession session = request.getSession();
            Corpse corpse = (Corpse) session.getAttribute("corpse");
            java.lang.String snippetID = request.getParameter("snippetID");
            CorpseLyric corpseLyric = (CorpseLyric) session.getAttribute(snippetID);
            corpse.removeLyricSnippet(corpseLyric);

            url = "/corpse.jsp";

        }

        else if (action.equals("share")) {
            HttpSession session = request.getSession();
            Corpse corpse = (Corpse) session.getAttribute("corpse");

            ArrayList<CorpseLyric> corpseLyrics = corpse.getCorpseLyrics();
            CorpseID corpseID = new CorpseID();

            SharedCorpse sharedCorpse = new SharedCorpse(corpseLyrics, corpseID.getCorpseID());
            ObjectifyService.ofy().save().entity(sharedCorpse).now();



            ShareCorpseMessage message = new ShareCorpseMessage(corpseID.getCorpseID());
            //print message for testing
            System.out.println(message.getMessage());




        }

        //forward request and response objects to specified URL
        servletContext.getRequestDispatcher(url).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    protected java.lang.String getMusixMatchAPIKey() {
        //used to read in the musixmatch API key from a file
        java.lang.String apiKey = "";
        try {
            FileReader fileReader = new FileReader("mmapikey.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            apiKey = bufferedReader.readLine();
            fileReader.close();


        }
        catch (IOException ioe) {
            System.out.println("Could not open or read mmapikey.txt");
            System.out.println(ioe.toString());
        }
        return apiKey;
    }

}
