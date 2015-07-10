package com.twenty48;

//import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StartServlet extends HttpServlet {
    Game game = new Game(GameState.Game);
    public static final long serialVersionID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        /*
        List<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        String json = new Gson().toJson(list);
        */

        int direction =  Integer.parseInt(req.getParameter("direction"));

        if(direction == 0)
            game = new Game(GameState.Game); // start new game
        String answer = game.run(direction);

        String[] lines = answer.split("\n");


        //res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");


        PrintWriter out = res.getWriter();
        for(String s : lines){
            out.println(s);
            out.println("<br>");
        }

        out.close();

    }


}