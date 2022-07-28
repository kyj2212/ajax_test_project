package com.yejin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        //super.doGet(req, resp);

        Rq rq = new Rq(req,resp);

        rq.appendBody("<title>구구단</title>");

        int dan = rq.getIntParam("dan",9);
        int limit = rq.getIntParam("limit",9);

//        rq.appendBody("<h1>%d단</h1>\n".formatted(dan));
//        for(int i=1;i<=limit;i++)
//         rq.appendBody("<div>"+dan+"*"+i+"="+dan*i+"</div>");

        req.setAttribute("dan",dan);
        req.setAttribute("limit",limit);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/gugudan2.jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


