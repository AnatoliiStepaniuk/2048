package com.twenty48.Servlets;

import com.google.gson.Gson;
import com.twenty48.Classes.Game;
import com.twenty48.Classes.Score;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class GetScoreServlet extends HttpServlet {
    public static final long serialVersionID = 1L;
    private Gson gson = new Gson();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true); // if there's no session object, the one will be created
        session.setMaxInactiveInterval(30*60*60); // inactive lifetime of session object (in seconds)
        Game currentGame = (Game)session.getAttribute("game");
        Score score = (Score)session.getAttribute("score");

        if(score == null) {
            session.setAttribute("score", new Score());
        }
        if(currentGame == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet/NewGame");
            dispatcher.forward(request, response);
        }
        else {
            score.setScore(score.getCurrentScore() + currentGame.getScoreIncrease());
            String JSONscore = gson.toJson(score);
            PrintWriter out = response.getWriter();
            out.println(JSONscore);
            out.close();
        }
    }
}
