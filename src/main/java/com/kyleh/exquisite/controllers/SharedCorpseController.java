package com.kyleh.exquisite.controllers;

import com.googlecode.objectify.ObjectifyService;

import com.googlecode.objectify.Result;
import com.kyleh.exquisite.business.CorpseLyric;
import com.kyleh.exquisite.business.SharedCorpse;
import com.kyleh.exquisite.utility.ExquisiteConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kylehebert on 5/6/15.
 * Servlet responsible for displaying the shared corpse based on
 * the ID parameter passed by a shared corpse URL
 */



public class SharedCorpseController extends HttpServlet {

    //register class for Objectify
    static {
        ObjectifyService.register(SharedCorpse.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = ExquisiteConstants.SHARED_URL;
        ServletContext servletContext = getServletContext();

        //get current action
        String action = request.getParameter(ExquisiteConstants.ACTION);
        if (action == null) {
            action = ExquisiteConstants.DISPLAY; //default action
        }

        if (action.equals(ExquisiteConstants.DISPLAY)) {

            //retrieve the corpse id from the url
            long sharedID = Long.parseLong(request.getParameter(ExquisiteConstants.ID));

            SharedCorpse sharedCorpse = ObjectifyService.ofy().load().type(SharedCorpse.class).id(sharedID).now();
            url = ExquisiteConstants.SHARED_URL;
            request.setAttribute(ExquisiteConstants.SHARED_CORPSE_ATT, sharedCorpse);
        }

        //forward request and response objects to specified URL
        servletContext.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
