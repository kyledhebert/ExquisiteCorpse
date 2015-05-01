package com.kyleh.exquisite;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.jmusixmatch.snippet.Snippet;
import org.jmusixmatch.subtitle.Subtitle;

/**
 * Created by kylehebert on 4/24/15.
 */
public class ServletExquisite extends HttpServlet {

    TrackData trackData;
    Track track;
    Lyrics lyrics;
    Snippet snippet;


    MusixMatch musixMatch = new MusixMatch(getMusixMatchAPIKey());
    SearchResult searchResult;

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
            String lyricSnippet = lyrics.getLyricsBody();

            //store results in SearchResult object and create a session
            HttpSession session = request.getSession();
            SearchResult searchResult = new SearchResult(artist,track,lyricSnippet);
            session.setAttribute("result", searchResult);

            //validate the search parameters
            String message;
            if (artist == null || track == null || artist.isEmpty() || track.isEmpty()) {
                message = "Please enter text into both boxes.";
                url = "/index.jsp";
            }

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
                corpse = new Corpse();
            }

            String included = request.getParameter("include");
            SearchResult searchResult = (SearchResult) session.getAttribute("result");

            CorpseLyric corpseLyric = new CorpseLyric(searchResult.getSnippet());
            corpseLyric.setSnippetIncluded(included);
            if (included.equals("true")) {
                corpse.addLyricSnippet(corpseLyric);
            } else if (included.equals("false")) {
                corpse.removeLyricSnippet(corpseLyric);
            }

            session.setAttribute("corpse", corpse);
            url = "/corpse.jsp";

        }

        //forward request and response objects to specified URL
        servletContext.getRequestDispatcher(url).forward(request,response);
    }

    @Override
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
