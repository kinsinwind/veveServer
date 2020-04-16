package com.kinsin.servlets;


import com.kinsin.domain.Friend;
import com.kinsin.service.FriendService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addFriend" ,urlPatterns = "/addFriend")
public class AddFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account1="",account2="";
        account1 = request.getParameter("account1");
        account2 = request.getParameter("account2");

        Friend friend=new Friend(account1,account2);

        FriendService friendService=new FriendService();
        JSONObject jsonObject=new JSONObject();

        if(friendService.addFriend(friend) ){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);



    }
}
