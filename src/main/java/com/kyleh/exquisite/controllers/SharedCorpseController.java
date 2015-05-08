package com.kyleh.exquisite.controllers;

import com.googlecode.objectify.ObjectifyService;

import com.googlecode.objectify.Result;
import com.kyleh.exquisite.business.CorpseLyric;
import com.kyleh.exquisite.business.SharedCorpse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kylehebert on 5/6/15.
 * Servlet responsible for displaying the shared corpse based on URL
 */



public class SharedCorpseController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/shared.jsp";
        ServletContext servletContext = getServletContext();

        //get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "display"; //default action
        }

        if (action.equals("display")) {

            //retrieve the hashed corpse id from the url
            String sharedID = request.getParameter("id");

            Result<SharedCorpse> sharedCorpseResult = ObjectifyService.ofy().load().type(SharedCorpse.class).id(sharedID);
            SharedCorpse sharedCorpse = ObjectifyService.ofy().load().type(SharedCorpse.class).id(sharedID).now();

            url = "/shared.jsp";
        }

        //forward request and response objects to specified URL
        servletContext.getRequestDispatcher(url).forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
