package com.kyleh.exquisite;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

/**
 * Created by kylehebert on 4/24/15.
 */
public class ServletExquisite extends HttpServlet {

    TrackData trackData;
    Track track;
    Lyrics lyrics;


    MusixMatch musixMatch = new MusixMatch(getMusixMatchAPIKey());
    SearchResult searchResult;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/index.html";

        //get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "search"; //default action
        }

        //perform action and set URL to appropriate page
        if (action.equals("search")) {
            url = "/index.html"; //the search page
        }
        //search for and display the lyric snippet at result.jsp
        else if (action.equals("result")) {
            String artistSearch = request.getParameter("artist");
            String trackSearch = request.getParameter("track");


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

            //lyrics search
            int trackID = trackData.getTrackId();

            try {
                lyrics = musixMatch.getLyrics(trackID);
            } catch (MusixMatchException e) {
                e.printStackTrace();
            }
            String snippet = lyrics.getLyricsBody();

            //store results in SearchResult object
            SearchResult searchResult = new SearchResult(artist,track,snippet);

            //set SearchResult Object in request object and set URL
            request.setAttribute("searchResult", searchResult);
            url = "/result.jsp"; //the results page

        }

        //convert result to CorpseLyric object and add to Corpse object
        //TODO will need to check if corpse exist and if not create a new corpse first
        else if (action.equals("add")) {

            String snippet = searchResult.getSnippet();

            Corpse corpse = new Corpse();


        }

        else if(action.equals("view")) {
            url = "/corpse.jsp"; //the corpse view page
        }





        //forward request and response objects to specified URL
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    protected String getMusixMatchAPIKey() {
        //used to read in the musixmatch API key from a file
        String apiKey = "";
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
