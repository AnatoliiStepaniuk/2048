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
        int direction =  Integer.parseInt(request.getParameter("direction"));

        if(direction == 0)
            game = new Game(GameState.Game); // start new game
        String answer = game.run(direction);
        String[] lines = answer.split("\n");
        StringBuilder sb = new StringBuilder();

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        sb.append("{\"board\":[");
            for(int j = 0; j < lines.length; j++){
            String[] temp = lines[j].split(" ");
            sb.append("[");
            for(int i = 0; i < temp.length; i++){
                sb.append(temp[i]);
                if(i != temp.length-1)
                    sb.append(",");
            }
            sb.append("]");
            if(j != lines.length-1)
                sb.append(",");
        }

        sb.append("]");
        sb.append("}");
        out.println(sb.toString());
/*
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

        out.close();
    }

}