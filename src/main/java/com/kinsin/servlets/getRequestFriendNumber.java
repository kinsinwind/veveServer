package com.kinsin.servlets;

import com.kinsin.domain.RequestFriend;
import com.kinsin.service.RequestFriendService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getRequestFriendNumber" ,urlPatterns = "/getRequestFriendNumber")
public class getRequestFriendNumber extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String uid2="";
        uid2 = request.getParameter("uid2");
        JSONObject jsonObject=new JSONObject();
        RequestFriend requestFriend=new RequestFriend();
        requestFriend.setUid2(uid2);
        try{
            RequestFriendService requestFriendService=new RequestFriendService();
            jsonObject.put("RESULT","S");
            jsonObject.put("num",requestFriendService.getRequestFriendNumber(requestFriend));
        }catch (Exception e){
            jsonObject.put("RESULT","F");
        }
        writer.print(jsonObject);
    }
}
