package com.twenty48;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class StartServlet extends HttpServlet  {
    Game game = new Game(GameState.Game);
    public static final long serialVersionID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true); // if there's no session object, the one will be created
        session.setMaxInactiveInterval(30*60); // inactive lifetime of session object (in seconds)
        Object value = session.getAttribute("servlet");
        if(value == null){
            StartServlet newServlet = new StartServlet();
            session.setAttribute("servlet", newServlet);
        }

        StartServlet clientsServlet = (StartServlet)session.getAttribute("servlet");

        clientsServlet.go(request, response);

    }

    public void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String direction =  request.getParameter("direction");
//        int direction =  Integer.parseInt(request.getParameter("direction"));

        if(direction.equals("NewGame"))
            game = new Game(GameState.Game); // start new game
        String answer = game.run(direction);

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String[] lines = answer.split("\n");
        StringBuilder sb = new StringBuilder();

        sb.append("{\"currentScore\":" + Score.getCurrentScore() + ",");
        sb.append("\"bestScore\":" + Score.getBestScore() + ",");
        sb.append("\"tiles\":[");
        for(int j = 0; j < lines.length; j++){
            String [] tileObject = lines[j].split(" ");
            sb.append("{");

                sb.append("\"value\":" + tileObject[0] + ",");
                sb.append("\"x\":" + tileObject[1] + ",");
                sb.append("\"y\":" + tileObject[2] + ",");
                sb.append("\"prevX\":" + tileObject[3] + ",");
                sb.append("\"prevY\":" + tileObject[4] + ",");
                sb.append("\"merged\":" + tileObject[5]);

            sb.append("}");
            if(j != lines.length-1)
                sb.append(",");
        }
        sb.append("]}");

        out.println(sb.toString());
        out.close();
    }

}

/*
{
  "tiles":[
    {
            "value":"4",
            "x": "0",
            "y": "1",
            "prevX": "1",
            "prevY": "1",
            "merged": "true"
    },
    {
            "value":"4",
            "x": "0",
            "y": "1",
            "prevX": "1",
            "prevY": "1",
            "merged": "true"
    },
    {
            "value":"4",
            "x": "0",
            "y": "1",
            "prevX": "1",
            "prevY": "1",
            "merged": "true"
    },
    {
            "value":"4",
            "x": "0",
            "y": "1",
            "prevX": "1",
            "prevY": "1",
            "merged": "true"
    }
  ]
}



    output in format:

{
  "board":[
    [0,2,4,4],
    [0,0,0,0],
    [0,0,0,0],
    [0,0,0,0]
  ]
}

*/