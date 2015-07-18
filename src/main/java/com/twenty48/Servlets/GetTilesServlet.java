package com.twenty48.Servlets;

import com.twenty48.Classes.Game;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class GetTilesServlet extends HttpServlet  {
    public static final long serialVersionID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true); // if there's no session object, the one will be created
        session.setMaxInactiveInterval(30*60*60); // inactive lifetime of session object (in seconds)
        Game currentGame = (Game)session.getAttribute("game");
        if(currentGame == null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/NewGame");
            dispatcher.forward(request, response);
        }
        else {
            String JSONtiles = currentGame.getTiles();
            PrintWriter out = response.getWriter();
            out.println(JSONtiles);
            out.close();
        }
    }

}