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
import java.util.List;

@WebServlet(name = "getRequestFriendByUid2" ,urlPatterns = "/getRequestFriendByUid2")
public class getRequestFriendByUid2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer=response.getWriter();
        String uid2="";
        uid2 = request.getParameter("uid2");
        JSONObject jsonObject=new JSONObject();
        try{
            RequestFriend requestFriend=new RequestFriend();
            requestFriend.setUid2(uid2);
            requestFriend.setIswork(1);
            RequestFriendService requestFriendService=new RequestFriendService();
            List<RequestFriend> list = requestFriendService.getRequestByUid2(requestFriend);
            jsonObject.put("RESULT","S");
            jsonObject.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("RESULT","F");
        }

        writer.print(jsonObject);

    }
}
