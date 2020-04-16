package com.kinsin.servlets;

import com.kinsin.domain.Friend;
import com.kinsin.domain.User;
import com.kinsin.service.FriendService;
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

@WebServlet(name = "getFriend" ,urlPatterns = "/getFriend")
public class getFriend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String account1="";
        String account2="";
        account1 = request.getParameter("account1");
        account2 = request.getParameter("account2");
        JSONObject jsonObject=new JSONObject();
        Friend friend=new Friend(account1,account2);
        FriendService friendService=new FriendService();
        if(friendService.getFriendByUidAndFid(friend)!=null){
            jsonObject.put("RESULT","S");
        }else {
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
