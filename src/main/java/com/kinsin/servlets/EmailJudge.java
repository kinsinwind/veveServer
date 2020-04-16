package com.kinsin.servlets;

import com.kinsin.domain.User;
import com.kinsin.service.UserService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "emailJudge" ,urlPatterns = "/emailJudge")
public class EmailJudge extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String email="";
        email = request.getParameter("email");

        UserService userService = new UserService();

        User user = new User(null,null,email,null,0);
        JSONObject jsonObject=new JSONObject();

        if(userService.getUserByEmail(user)==null){//是否可以使用
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
