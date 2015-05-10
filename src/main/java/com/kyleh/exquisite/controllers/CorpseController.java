package com.kyleh.exquisite.controllers;

import com.googlecode.objectify.Result;
import com.kyleh.exquisite.business.*;
import com.kyleh.exquisite.utility.CorpseID;

import com.kyleh.exquisite.utility.ShareCorpse;
import com.kyleh.exquisite.utility.ShareCorpseMessage;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
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
 * to and Sharing a Corpse object.
 */
public class CorpseController extends HttpServlet {

    TrackData trackData;
    Track track;
    Snippet snippet;

    MusixMatch musixMatch = new MusixMatch(getMusixMatchAPIKey());

    //register class for Objectify
    static {
        ObjectifyService.register(SharedCorpse.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/index.jsp";
        ServletContext servletContext = getServletContext();

        //get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "search"; //default action
        }

        //perform action and set URL to appropriate page
        if (action.equals("search")) {
            url = "/index.jsp"; //the search page
        }

        if (action.equals("reset")) {
            //remove the old corspe session, so new ones can be created
            HttpSession session = request.getSession();
            session.removeAttribute("corpse");
            url ="/index.jsp";
        }

        //search for and display the lyric snippet at result.jsp
        else if (action.equals("result")) {

            //get the search parameters and store them in a SnippetSearch object
            String artistSearch = request.getParameter("artist");
            String trackSearch = request.getParameter("track");

            //SnippetSearch snippetSearch = new SnippetSearch(artistSearch,trackSearch);

            //validate the search parameters
            String message = "";
            if (artistSearch == null || trackSearch == null || artistSearch.isEmpty() || trackSearch.isEmpty()) {
                message = "Please enter both a track and artist";
                url = "/index.jsp";
            }
            else {
                //Fuzzy Search
                try {
                    track = musixMatch.getMatchingTrack(trackSearch, artistSearch);

                } catch (MusixMatchException e) {
                    System.out.println("Artist or track not found");
                    e.printStackTrace();

                }

                trackData = track.getTrack();

                String artist = trackData.getArtistName();
                String track = trackData.getTrackName();


                //snippet search
                int trackID = trackData.getTrackId();
                String resultID = Integer.toString(trackID);

                try {
                    snippet = musixMatch.getSnippet(trackID);
                } catch (MusixMatchException e) {
                    e.printStackTrace();
                }
                String lyricSnippet = snippet.getSnippetBody();

                //store results in SearchResult object and create a session
                HttpSession session = request.getSession();
                SearchResult searchResult = new SearchResult(resultID,artist,track,lyricSnippet);

                session.setAttribute("result", searchResult);

                //set SearchResult Object in request object and set URL
                request.setAttribute("searchResult", searchResult);
                url = "/result.jsp"; //the results page

            }
            request.setAttribute("message", message);
        }

        //convert result to CorpseLyric object and add to Corpse object
        else if (action.equals("add")) {

            //String snippet = searchResult.getSnippet();

            //create a a new corpse and new corpse session if needed
            HttpSession session = request.getSession();
            Corpse corpse = (Corpse) session.getAttribute("corpse");
            if (corpse == null) {
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
            String snippetID = request.getParameter("snippetID");
            CorpseLyric corpseLyric = (CorpseLyric) session.getAttribute(snippetID);
            corpse.removeLyricSnippet(corpseLyric);

            url = "/corpse.jsp";

        }

        else if (action.equals("share")) {
            HttpSession session = request.getSession();
            Corpse corpse = (Corpse) session.getAttribute("corpse");

            ArrayList<CorpseLyric> corpseLyrics = corpse.getCorpseLyrics();
            CorpseID corpseID = new CorpseID();

            //create a SharedCorpse and add it to the Data Store
            SharedCorpse sharedCorpse = new SharedCorpse(corpseLyrics, corpseID.getCorpseID());
            ObjectifyService.ofy().save().entity(sharedCorpse).now();

            //Create the share message for Twitter
            ShareCorpseMessage message = new ShareCorpseMessage(corpseID.getCorpseID());

            //Share the URL on Twitter
            ShareCorpse shareCorpse = new ShareCorpse();
            shareCorpse.shareCorpseOnTwitter(message);

            //create the share URL for the Success page
            String sharedURL = sharedCorpseURL(corpseID);
            request.setAttribute("sharedURL", sharedURL);

            url = "/success.jsp";
        }

        //forward request and response objects to specified URL
        servletContext.getRequestDispatcher(url).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    protected String sharedCorpseURL(CorpseID corpseID){
        return "http://localhost:8080/share?id=" + corpseID.getCorpseID();
    }

    protected String getMusixMatchAPIKey() {
        //used to read in the musixmatch API key from a file
        String apiKey = "";
        try {
            FileReader fileReader = new FileReader("mmapikey.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            apiKey = bufferedReader.readLine();
            bufferedReader.close();
            fileReader.close();


        }
        catch (IOException ioe) {
            System.out.println("Could not open or read mmapikey.txt");
            System.out.println(ioe.toString());
        }
        return apiKey;
    }

}
