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
import java.util.List;

@WebServlet(name = "getUserByAccount" ,urlPatterns = "/getUserByAccount")
public class getUserByAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account="";
        account = request.getParameter("account");
        User user=new User(account,"","","",0);
        UserService userService = new UserService();
        JSONObject jsonObject=new JSONObject();
        User user1 = userService.getUserByAccount(user);
        if(user1!=null){
            jsonObject.put("RESULT","S");
            jsonObject.put("account",user1.getAccount());
            jsonObject.put("nick_name",user1.getNick_name());
            jsonObject.put("net_status",user1.getNet_status());
            jsonObject.put("headicon",user1.getHeadicon());
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
